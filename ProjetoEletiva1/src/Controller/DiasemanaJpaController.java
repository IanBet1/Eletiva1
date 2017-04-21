/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Diasemana;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author ianbe
 */
public class DiasemanaJpaController implements Serializable {

    public DiasemanaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Diasemana diasemana) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(diasemana);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiasemana(diasemana.getIddiasemana()) != null) {
                throw new PreexistingEntityException("Diasemana " + diasemana + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Diasemana diasemana) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            diasemana = em.merge(diasemana);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = diasemana.getIddiasemana();
                if (findDiasemana(id) == null) {
                    throw new NonexistentEntityException("The diasemana with id " + id + " no longer exists.");
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
            Diasemana diasemana;
            try {
                diasemana = em.getReference(Diasemana.class, id);
                diasemana.getIddiasemana();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diasemana with id " + id + " no longer exists.", enfe);
            }
            em.remove(diasemana);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Diasemana> findDiasemanaEntities() {
        return findDiasemanaEntities(true, -1, -1);
    }

    public List<Diasemana> findDiasemanaEntities(int maxResults, int firstResult) {
        return findDiasemanaEntities(false, maxResults, firstResult);
    }

    private List<Diasemana> findDiasemanaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Diasemana.class));
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

    public Diasemana findDiasemana(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Diasemana.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiasemanaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Diasemana> rt = cq.from(Diasemana.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
