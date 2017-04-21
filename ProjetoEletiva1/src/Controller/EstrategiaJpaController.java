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
import Beans.Estrategia;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ianbe
 */
public class EstrategiaJpaController implements Serializable {

    public EstrategiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estrategia estrategia) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areaconhecimento areaconhecimentoIdconhecimento = estrategia.getAreaconhecimentoIdconhecimento();
            if (areaconhecimentoIdconhecimento != null) {
                areaconhecimentoIdconhecimento = em.getReference(areaconhecimentoIdconhecimento.getClass(), areaconhecimentoIdconhecimento.getIdconhecimento());
                estrategia.setAreaconhecimentoIdconhecimento(areaconhecimentoIdconhecimento);
            }
            em.persist(estrategia);
            if (areaconhecimentoIdconhecimento != null) {
                areaconhecimentoIdconhecimento.getEstrategiaList().add(estrategia);
                areaconhecimentoIdconhecimento = em.merge(areaconhecimentoIdconhecimento);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findEstrategia(estrategia.getIdestrategia()) != null) {
                throw new PreexistingEntityException("Estrategia " + estrategia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estrategia estrategia) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estrategia persistentEstrategia = em.find(Estrategia.class, estrategia.getIdestrategia());
            Areaconhecimento areaconhecimentoIdconhecimentoOld = persistentEstrategia.getAreaconhecimentoIdconhecimento();
            Areaconhecimento areaconhecimentoIdconhecimentoNew = estrategia.getAreaconhecimentoIdconhecimento();
            if (areaconhecimentoIdconhecimentoNew != null) {
                areaconhecimentoIdconhecimentoNew = em.getReference(areaconhecimentoIdconhecimentoNew.getClass(), areaconhecimentoIdconhecimentoNew.getIdconhecimento());
                estrategia.setAreaconhecimentoIdconhecimento(areaconhecimentoIdconhecimentoNew);
            }
            estrategia = em.merge(estrategia);
            if (areaconhecimentoIdconhecimentoOld != null && !areaconhecimentoIdconhecimentoOld.equals(areaconhecimentoIdconhecimentoNew)) {
                areaconhecimentoIdconhecimentoOld.getEstrategiaList().remove(estrategia);
                areaconhecimentoIdconhecimentoOld = em.merge(areaconhecimentoIdconhecimentoOld);
            }
            if (areaconhecimentoIdconhecimentoNew != null && !areaconhecimentoIdconhecimentoNew.equals(areaconhecimentoIdconhecimentoOld)) {
                areaconhecimentoIdconhecimentoNew.getEstrategiaList().add(estrategia);
                areaconhecimentoIdconhecimentoNew = em.merge(areaconhecimentoIdconhecimentoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estrategia.getIdestrategia();
                if (findEstrategia(id) == null) {
                    throw new NonexistentEntityException("The estrategia with id " + id + " no longer exists.");
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
            Estrategia estrategia;
            try {
                estrategia = em.getReference(Estrategia.class, id);
                estrategia.getIdestrategia();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estrategia with id " + id + " no longer exists.", enfe);
            }
            Areaconhecimento areaconhecimentoIdconhecimento = estrategia.getAreaconhecimentoIdconhecimento();
            if (areaconhecimentoIdconhecimento != null) {
                areaconhecimentoIdconhecimento.getEstrategiaList().remove(estrategia);
                areaconhecimentoIdconhecimento = em.merge(areaconhecimentoIdconhecimento);
            }
            em.remove(estrategia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estrategia> findEstrategiaEntities() {
        return findEstrategiaEntities(true, -1, -1);
    }

    public List<Estrategia> findEstrategiaEntities(int maxResults, int firstResult) {
        return findEstrategiaEntities(false, maxResults, firstResult);
    }

    private List<Estrategia> findEstrategiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estrategia.class));
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

    public Estrategia findEstrategia(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estrategia.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstrategiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estrategia> rt = cq.from(Estrategia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
