/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Beans.Categoria;
import Beans.Classe;
import java.util.ArrayList;
import java.util.List;
import Beans.Planoaula;
import Beans.Avaliacao;
import Beans.Usuario;
import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Terminal
 */
public class UsuarioJpaController2 implements Serializable {

    public UsuarioJpaController2(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getClasseList() == null) {
            usuario.setClasseList(new ArrayList<Classe>());
        }
        if (usuario.getPlanoaulaList() == null) {
            usuario.setPlanoaulaList(new ArrayList<Planoaula>());
        }
        if (usuario.getAvaliacaoCollection() == null) {
            usuario.setAvaliacaoCollection(new ArrayList<Avaliacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaIdcategoria = usuario.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria = em.getReference(categoriaIdcategoria.getClass(), categoriaIdcategoria.getIdcategoria());
                usuario.setCategoriaIdcategoria(categoriaIdcategoria);
            }
            List<Classe> attachedClasseList = new ArrayList<Classe>();
            for (Classe classeListClasseToAttach : usuario.getClasseList()) {
                classeListClasseToAttach = em.getReference(classeListClasseToAttach.getClass(), classeListClasseToAttach.getIdclasse());
                attachedClasseList.add(classeListClasseToAttach);
            }
            usuario.setClasseList(attachedClasseList);
            List<Planoaula> attachedPlanoaulaList = new ArrayList<Planoaula>();
            for (Planoaula planoaulaListPlanoaulaToAttach : usuario.getPlanoaulaList()) {
                planoaulaListPlanoaulaToAttach = em.getReference(planoaulaListPlanoaulaToAttach.getClass(), planoaulaListPlanoaulaToAttach.getIdplanoaula());
                attachedPlanoaulaList.add(planoaulaListPlanoaulaToAttach);
            }
            usuario.setPlanoaulaList(attachedPlanoaulaList);
            Collection<Avaliacao> attachedAvaliacaoCollection = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaoCollectionAvaliacaoToAttach : usuario.getAvaliacaoCollection()) {
                avaliacaoCollectionAvaliacaoToAttach = em.getReference(avaliacaoCollectionAvaliacaoToAttach.getClass(), avaliacaoCollectionAvaliacaoToAttach.getIdavaliacao());
                attachedAvaliacaoCollection.add(avaliacaoCollectionAvaliacaoToAttach);
            }
            usuario.setAvaliacaoCollection(attachedAvaliacaoCollection);
            em.persist(usuario);
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getUsuarioList().add(usuario);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            for (Classe classeListClasse : usuario.getClasseList()) {
                Usuario oldProfessorOfClasseListClasse = classeListClasse.getProfessor();
                classeListClasse.setProfessor(usuario);
                classeListClasse = em.merge(classeListClasse);
                if (oldProfessorOfClasseListClasse != null) {
                    oldProfessorOfClasseListClasse.getClasseList().remove(classeListClasse);
                    oldProfessorOfClasseListClasse = em.merge(oldProfessorOfClasseListClasse);
                }
            }
            for (Planoaula planoaulaListPlanoaula : usuario.getPlanoaulaList()) {
                Usuario oldUsuarioLoginOfPlanoaulaListPlanoaula = planoaulaListPlanoaula.getUsuarioLogin();
                planoaulaListPlanoaula.setUsuarioLogin(usuario);
                planoaulaListPlanoaula = em.merge(planoaulaListPlanoaula);
                if (oldUsuarioLoginOfPlanoaulaListPlanoaula != null) {
                    oldUsuarioLoginOfPlanoaulaListPlanoaula.getPlanoaulaList().remove(planoaulaListPlanoaula);
                    oldUsuarioLoginOfPlanoaulaListPlanoaula = em.merge(oldUsuarioLoginOfPlanoaulaListPlanoaula);
                }
            }
            for (Avaliacao avaliacaoCollectionAvaliacao : usuario.getAvaliacaoCollection()) {
                Usuario oldUsuarioLoginOfAvaliacaoCollectionAvaliacao = avaliacaoCollectionAvaliacao.getUsuarioLogin();
                avaliacaoCollectionAvaliacao.setUsuarioLogin(usuario);
                avaliacaoCollectionAvaliacao = em.merge(avaliacaoCollectionAvaliacao);
                if (oldUsuarioLoginOfAvaliacaoCollectionAvaliacao != null) {
                    oldUsuarioLoginOfAvaliacaoCollectionAvaliacao.getAvaliacaoCollection().remove(avaliacaoCollectionAvaliacao);
                    oldUsuarioLoginOfAvaliacaoCollectionAvaliacao = em.merge(oldUsuarioLoginOfAvaliacaoCollectionAvaliacao);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getLogin()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getLogin());
            Categoria categoriaIdcategoriaOld = persistentUsuario.getCategoriaIdcategoria();
            Categoria categoriaIdcategoriaNew = usuario.getCategoriaIdcategoria();
            List<Classe> classeListOld = persistentUsuario.getClasseList();
            List<Classe> classeListNew = usuario.getClasseList();
            List<Planoaula> planoaulaListOld = persistentUsuario.getPlanoaulaList();
            List<Planoaula> planoaulaListNew = usuario.getPlanoaulaList();
            Collection<Avaliacao> avaliacaoCollectionOld = persistentUsuario.getAvaliacaoCollection();
            Collection<Avaliacao> avaliacaoCollectionNew = usuario.getAvaliacaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Classe classeListOldClasse : classeListOld) {
                if (!classeListNew.contains(classeListOldClasse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Classe " + classeListOldClasse + " since its professor field is not nullable.");
                }
            }
            for (Planoaula planoaulaListOldPlanoaula : planoaulaListOld) {
                if (!planoaulaListNew.contains(planoaulaListOldPlanoaula)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Planoaula " + planoaulaListOldPlanoaula + " since its usuarioLogin field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (categoriaIdcategoriaNew != null) {
                categoriaIdcategoriaNew = em.getReference(categoriaIdcategoriaNew.getClass(), categoriaIdcategoriaNew.getIdcategoria());
                usuario.setCategoriaIdcategoria(categoriaIdcategoriaNew);
            }
            List<Classe> attachedClasseListNew = new ArrayList<Classe>();
            for (Classe classeListNewClasseToAttach : classeListNew) {
                classeListNewClasseToAttach = em.getReference(classeListNewClasseToAttach.getClass(), classeListNewClasseToAttach.getIdclasse());
                attachedClasseListNew.add(classeListNewClasseToAttach);
            }
            classeListNew = attachedClasseListNew;
            usuario.setClasseList(classeListNew);
            List<Planoaula> attachedPlanoaulaListNew = new ArrayList<Planoaula>();
            for (Planoaula planoaulaListNewPlanoaulaToAttach : planoaulaListNew) {
                planoaulaListNewPlanoaulaToAttach = em.getReference(planoaulaListNewPlanoaulaToAttach.getClass(), planoaulaListNewPlanoaulaToAttach.getIdplanoaula());
                attachedPlanoaulaListNew.add(planoaulaListNewPlanoaulaToAttach);
            }
            planoaulaListNew = attachedPlanoaulaListNew;
            usuario.setPlanoaulaList(planoaulaListNew);
            Collection<Avaliacao> attachedAvaliacaoCollectionNew = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaoCollectionNewAvaliacaoToAttach : avaliacaoCollectionNew) {
                avaliacaoCollectionNewAvaliacaoToAttach = em.getReference(avaliacaoCollectionNewAvaliacaoToAttach.getClass(), avaliacaoCollectionNewAvaliacaoToAttach.getIdavaliacao());
                attachedAvaliacaoCollectionNew.add(avaliacaoCollectionNewAvaliacaoToAttach);
            }
            avaliacaoCollectionNew = attachedAvaliacaoCollectionNew;
            usuario.setAvaliacaoCollection(avaliacaoCollectionNew);
            usuario = em.merge(usuario);
            if (categoriaIdcategoriaOld != null && !categoriaIdcategoriaOld.equals(categoriaIdcategoriaNew)) {
                categoriaIdcategoriaOld.getUsuarioList().remove(usuario);
                categoriaIdcategoriaOld = em.merge(categoriaIdcategoriaOld);
            }
            if (categoriaIdcategoriaNew != null && !categoriaIdcategoriaNew.equals(categoriaIdcategoriaOld)) {
                categoriaIdcategoriaNew.getUsuarioList().add(usuario);
                categoriaIdcategoriaNew = em.merge(categoriaIdcategoriaNew);
            }
            for (Classe classeListNewClasse : classeListNew) {
                if (!classeListOld.contains(classeListNewClasse)) {
                    Usuario oldProfessorOfClasseListNewClasse = classeListNewClasse.getProfessor();
                    classeListNewClasse.setProfessor(usuario);
                    classeListNewClasse = em.merge(classeListNewClasse);
                    if (oldProfessorOfClasseListNewClasse != null && !oldProfessorOfClasseListNewClasse.equals(usuario)) {
                        oldProfessorOfClasseListNewClasse.getClasseList().remove(classeListNewClasse);
                        oldProfessorOfClasseListNewClasse = em.merge(oldProfessorOfClasseListNewClasse);
                    }
                }
            }
            for (Planoaula planoaulaListNewPlanoaula : planoaulaListNew) {
                if (!planoaulaListOld.contains(planoaulaListNewPlanoaula)) {
                    Usuario oldUsuarioLoginOfPlanoaulaListNewPlanoaula = planoaulaListNewPlanoaula.getUsuarioLogin();
                    planoaulaListNewPlanoaula.setUsuarioLogin(usuario);
                    planoaulaListNewPlanoaula = em.merge(planoaulaListNewPlanoaula);
                    if (oldUsuarioLoginOfPlanoaulaListNewPlanoaula != null && !oldUsuarioLoginOfPlanoaulaListNewPlanoaula.equals(usuario)) {
                        oldUsuarioLoginOfPlanoaulaListNewPlanoaula.getPlanoaulaList().remove(planoaulaListNewPlanoaula);
                        oldUsuarioLoginOfPlanoaulaListNewPlanoaula = em.merge(oldUsuarioLoginOfPlanoaulaListNewPlanoaula);
                    }
                }
            }
            for (Avaliacao avaliacaoCollectionOldAvaliacao : avaliacaoCollectionOld) {
                if (!avaliacaoCollectionNew.contains(avaliacaoCollectionOldAvaliacao)) {
                    avaliacaoCollectionOldAvaliacao.setUsuarioLogin(null);
                    avaliacaoCollectionOldAvaliacao = em.merge(avaliacaoCollectionOldAvaliacao);
                }
            }
            for (Avaliacao avaliacaoCollectionNewAvaliacao : avaliacaoCollectionNew) {
                if (!avaliacaoCollectionOld.contains(avaliacaoCollectionNewAvaliacao)) {
                    Usuario oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao = avaliacaoCollectionNewAvaliacao.getUsuarioLogin();
                    avaliacaoCollectionNewAvaliacao.setUsuarioLogin(usuario);
                    avaliacaoCollectionNewAvaliacao = em.merge(avaliacaoCollectionNewAvaliacao);
                    if (oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao != null && !oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao.equals(usuario)) {
                        oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao.getAvaliacaoCollection().remove(avaliacaoCollectionNewAvaliacao);
                        oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao = em.merge(oldUsuarioLoginOfAvaliacaoCollectionNewAvaliacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getLogin();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Classe> classeListOrphanCheck = usuario.getClasseList();
            for (Classe classeListOrphanCheckClasse : classeListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Classe " + classeListOrphanCheckClasse + " in its classeList field has a non-nullable professor field.");
            }
            List<Planoaula> planoaulaListOrphanCheck = usuario.getPlanoaulaList();
            for (Planoaula planoaulaListOrphanCheckPlanoaula : planoaulaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Planoaula " + planoaulaListOrphanCheckPlanoaula + " in its planoaulaList field has a non-nullable usuarioLogin field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Categoria categoriaIdcategoria = usuario.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getUsuarioList().remove(usuario);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            Collection<Avaliacao> avaliacaoCollection = usuario.getAvaliacaoCollection();
            for (Avaliacao avaliacaoCollectionAvaliacao : avaliacaoCollection) {
                avaliacaoCollectionAvaliacao.setUsuarioLogin(null);
                avaliacaoCollectionAvaliacao = em.merge(avaliacaoCollectionAvaliacao);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
