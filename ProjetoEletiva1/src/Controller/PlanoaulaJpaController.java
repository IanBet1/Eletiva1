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
import Beans.Classe;
import Beans.Planoaula;
import Beans.Usuario;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ianbe
 */
public class PlanoaulaJpaController implements Serializable {

    public PlanoaulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planoaula planoaula) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe classeIdclasse = planoaula.getClasseIdclasse();
            if (classeIdclasse != null) {
                classeIdclasse = em.getReference(classeIdclasse.getClass(), classeIdclasse.getIdclasse());
                planoaula.setClasseIdclasse(classeIdclasse);
            }
            Usuario usuarioLogin = planoaula.getUsuarioLogin();
            if (usuarioLogin != null) {
                usuarioLogin = em.getReference(usuarioLogin.getClass(), usuarioLogin.getLogin());
                planoaula.setUsuarioLogin(usuarioLogin);
            }
            em.persist(planoaula);
            if (classeIdclasse != null) {
                classeIdclasse.getPlanoaulaList().add(planoaula);
                classeIdclasse = em.merge(classeIdclasse);
            }
            if (usuarioLogin != null) {
                usuarioLogin.getPlanoaulaList().add(planoaula);
                usuarioLogin = em.merge(usuarioLogin);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanoaula(planoaula.getIdplanoaula()) != null) {
                throw new PreexistingEntityException("Planoaula " + planoaula + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Planoaula planoaula) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planoaula persistentPlanoaula = em.find(Planoaula.class, planoaula.getIdplanoaula());
            Classe classeIdclasseOld = persistentPlanoaula.getClasseIdclasse();
            Classe classeIdclasseNew = planoaula.getClasseIdclasse();
            Usuario usuarioLoginOld = persistentPlanoaula.getUsuarioLogin();
            Usuario usuarioLoginNew = planoaula.getUsuarioLogin();
            if (classeIdclasseNew != null) {
                classeIdclasseNew = em.getReference(classeIdclasseNew.getClass(), classeIdclasseNew.getIdclasse());
                planoaula.setClasseIdclasse(classeIdclasseNew);
            }
            if (usuarioLoginNew != null) {
                usuarioLoginNew = em.getReference(usuarioLoginNew.getClass(), usuarioLoginNew.getLogin());
                planoaula.setUsuarioLogin(usuarioLoginNew);
            }
            planoaula = em.merge(planoaula);
            if (classeIdclasseOld != null && !classeIdclasseOld.equals(classeIdclasseNew)) {
                classeIdclasseOld.getPlanoaulaList().remove(planoaula);
                classeIdclasseOld = em.merge(classeIdclasseOld);
            }
            if (classeIdclasseNew != null && !classeIdclasseNew.equals(classeIdclasseOld)) {
                classeIdclasseNew.getPlanoaulaList().add(planoaula);
                classeIdclasseNew = em.merge(classeIdclasseNew);
            }
            if (usuarioLoginOld != null && !usuarioLoginOld.equals(usuarioLoginNew)) {
                usuarioLoginOld.getPlanoaulaList().remove(planoaula);
                usuarioLoginOld = em.merge(usuarioLoginOld);
            }
            if (usuarioLoginNew != null && !usuarioLoginNew.equals(usuarioLoginOld)) {
                usuarioLoginNew.getPlanoaulaList().add(planoaula);
                usuarioLoginNew = em.merge(usuarioLoginNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planoaula.getIdplanoaula();
                if (findPlanoaula(id) == null) {
                    throw new NonexistentEntityException("The planoaula with id " + id + " no longer exists.");
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
            Planoaula planoaula;
            try {
                planoaula = em.getReference(Planoaula.class, id);
                planoaula.getIdplanoaula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planoaula with id " + id + " no longer exists.", enfe);
            }
            Classe classeIdclasse = planoaula.getClasseIdclasse();
            if (classeIdclasse != null) {
                classeIdclasse.getPlanoaulaList().remove(planoaula);
                classeIdclasse = em.merge(classeIdclasse);
            }
            Usuario usuarioLogin = planoaula.getUsuarioLogin();
            if (usuarioLogin != null) {
                usuarioLogin.getPlanoaulaList().remove(planoaula);
                usuarioLogin = em.merge(usuarioLogin);
            }
            em.remove(planoaula);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Planoaula> findPlanoaulaEntities() {
        return findPlanoaulaEntities(true, -1, -1);
    }

    public List<Planoaula> findPlanoaulaEntities(int maxResults, int firstResult) {
        return findPlanoaulaEntities(false, maxResults, firstResult);
    }

    private List<Planoaula> findPlanoaulaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Planoaula.class));
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

    public Planoaula findPlanoaula(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Planoaula.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanoaulaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Planoaula> rt = cq.from(Planoaula.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
