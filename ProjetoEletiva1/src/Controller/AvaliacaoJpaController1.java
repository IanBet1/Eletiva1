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
import Beans.Areaconhecimento;
import Beans.Avaliacao;
import Beans.Usuario;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Terminal
 */
public class AvaliacaoJpaController1 implements Serializable {

    public AvaliacaoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Avaliacao avaliacao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areaconhecimento areaconhecimentoIdareaconhecimento = avaliacao.getAreaconhecimentoIdareaconhecimento();
            if (areaconhecimentoIdareaconhecimento != null) {
                areaconhecimentoIdareaconhecimento = em.getReference(areaconhecimentoIdareaconhecimento.getClass(), areaconhecimentoIdareaconhecimento.getIdconhecimento());
                avaliacao.setAreaconhecimentoIdareaconhecimento(areaconhecimentoIdareaconhecimento);
            }
            Usuario usuarioLogin = avaliacao.getUsuarioLogin();
            if (usuarioLogin != null) {
                usuarioLogin = em.getReference(usuarioLogin.getClass(), usuarioLogin.getLogin());
                avaliacao.setUsuarioLogin(usuarioLogin);
            }
            em.persist(avaliacao);
            if (areaconhecimentoIdareaconhecimento != null) {
                areaconhecimentoIdareaconhecimento.getAvaliacaoCollection().add(avaliacao);
                areaconhecimentoIdareaconhecimento = em.merge(areaconhecimentoIdareaconhecimento);
            }
            if (usuarioLogin != null) {
                usuarioLogin.getAvaliacaoCollection().add(avaliacao);
                usuarioLogin = em.merge(usuarioLogin);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAvaliacao(avaliacao.getIdavaliacao()) != null) {
                throw new PreexistingEntityException("Avaliacao " + avaliacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Avaliacao avaliacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacao persistentAvaliacao = em.find(Avaliacao.class, avaliacao.getIdavaliacao());
            Areaconhecimento areaconhecimentoIdareaconhecimentoOld = persistentAvaliacao.getAreaconhecimentoIdareaconhecimento();
            Areaconhecimento areaconhecimentoIdareaconhecimentoNew = avaliacao.getAreaconhecimentoIdareaconhecimento();
            Usuario usuarioLoginOld = persistentAvaliacao.getUsuarioLogin();
            Usuario usuarioLoginNew = avaliacao.getUsuarioLogin();
            if (areaconhecimentoIdareaconhecimentoNew != null) {
                areaconhecimentoIdareaconhecimentoNew = em.getReference(areaconhecimentoIdareaconhecimentoNew.getClass(), areaconhecimentoIdareaconhecimentoNew.getIdconhecimento());
                avaliacao.setAreaconhecimentoIdareaconhecimento(areaconhecimentoIdareaconhecimentoNew);
            }
            if (usuarioLoginNew != null) {
                usuarioLoginNew = em.getReference(usuarioLoginNew.getClass(), usuarioLoginNew.getLogin());
                avaliacao.setUsuarioLogin(usuarioLoginNew);
            }
            avaliacao = em.merge(avaliacao);
            if (areaconhecimentoIdareaconhecimentoOld != null && !areaconhecimentoIdareaconhecimentoOld.equals(areaconhecimentoIdareaconhecimentoNew)) {
                areaconhecimentoIdareaconhecimentoOld.getAvaliacaoCollection().remove(avaliacao);
                areaconhecimentoIdareaconhecimentoOld = em.merge(areaconhecimentoIdareaconhecimentoOld);
            }
            if (areaconhecimentoIdareaconhecimentoNew != null && !areaconhecimentoIdareaconhecimentoNew.equals(areaconhecimentoIdareaconhecimentoOld)) {
                areaconhecimentoIdareaconhecimentoNew.getAvaliacaoCollection().add(avaliacao);
                areaconhecimentoIdareaconhecimentoNew = em.merge(areaconhecimentoIdareaconhecimentoNew);
            }
            if (usuarioLoginOld != null && !usuarioLoginOld.equals(usuarioLoginNew)) {
                usuarioLoginOld.getAvaliacaoCollection().remove(avaliacao);
                usuarioLoginOld = em.merge(usuarioLoginOld);
            }
            if (usuarioLoginNew != null && !usuarioLoginNew.equals(usuarioLoginOld)) {
                usuarioLoginNew.getAvaliacaoCollection().add(avaliacao);
                usuarioLoginNew = em.merge(usuarioLoginNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = avaliacao.getIdavaliacao();
                if (findAvaliacao(id) == null) {
                    throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Avaliacao avaliacao;
            try {
                avaliacao = em.getReference(Avaliacao.class, id);
                avaliacao.getIdavaliacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The avaliacao with id " + id + " no longer exists.", enfe);
            }
            Areaconhecimento areaconhecimentoIdareaconhecimento = avaliacao.getAreaconhecimentoIdareaconhecimento();
            if (areaconhecimentoIdareaconhecimento != null) {
                areaconhecimentoIdareaconhecimento.getAvaliacaoCollection().remove(avaliacao);
                areaconhecimentoIdareaconhecimento = em.merge(areaconhecimentoIdareaconhecimento);
            }
            Usuario usuarioLogin = avaliacao.getUsuarioLogin();
            if (usuarioLogin != null) {
                usuarioLogin.getAvaliacaoCollection().remove(avaliacao);
                usuarioLogin = em.merge(usuarioLogin);
            }
            em.remove(avaliacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Avaliacao> findAvaliacaoEntities() {
        return findAvaliacaoEntities(true, -1, -1);
    }

    public List<Avaliacao> findAvaliacaoEntities(int maxResults, int firstResult) {
        return findAvaliacaoEntities(false, maxResults, firstResult);
    }

    private List<Avaliacao> findAvaliacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Avaliacao.class));
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

    public Avaliacao findAvaliacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Avaliacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAvaliacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Avaliacao> rt = cq.from(Avaliacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
