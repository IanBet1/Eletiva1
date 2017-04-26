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
import Beans.Diasemana;
import Beans.DiasemanaHasEstrategia;
import Beans.DiasemanaHasEstrategiaPK;
import Beans.Planoaula;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author IFSP
 */
public class DiasemanaHasEstrategiaJpaController implements Serializable {

    public DiasemanaHasEstrategiaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(DiasemanaHasEstrategia diasemanaHasEstrategia) throws PreexistingEntityException, Exception {
        if (diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK() == null) {
            diasemanaHasEstrategia.setDiasemanaHasEstrategiaPK(new DiasemanaHasEstrategiaPK());
        }
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setDiasemanaIddiasemana(diasemanaHasEstrategia.getDiasemana().getIddiasemana());
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setPlanoaulaIdplanoaula(diasemanaHasEstrategia.getPlanoaula().getIdplanoaula());
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setEstrategiaIdestrategia(diasemanaHasEstrategia.getEstrategia().getIdestrategia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Diasemana diasemana = diasemanaHasEstrategia.getDiasemana();
            if (diasemana != null) {
                diasemana = em.getReference(diasemana.getClass(), diasemana.getIddiasemana());
                diasemanaHasEstrategia.setDiasemana(diasemana);
            }
            Planoaula planoaula = diasemanaHasEstrategia.getPlanoaula();
            if (planoaula != null) {
                planoaula = em.getReference(planoaula.getClass(), planoaula.getIdplanoaula());
                diasemanaHasEstrategia.setPlanoaula(planoaula);
            }
            em.persist(diasemanaHasEstrategia);
            if (diasemana != null) {
                diasemana.getDiasemanaHasEstrategiaList().add(diasemanaHasEstrategia);
                diasemana = em.merge(diasemana);
            }
            if (planoaula != null) {
                planoaula.getDiasemanaHasEstrategiaList().add(diasemanaHasEstrategia);
                planoaula = em.merge(planoaula);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findDiasemanaHasEstrategia(diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK()) != null) {
                throw new PreexistingEntityException("DiasemanaHasEstrategia " + diasemanaHasEstrategia + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(DiasemanaHasEstrategia diasemanaHasEstrategia) throws NonexistentEntityException, Exception {
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setDiasemanaIddiasemana(diasemanaHasEstrategia.getDiasemana().getIddiasemana());
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setPlanoaulaIdplanoaula(diasemanaHasEstrategia.getPlanoaula().getIdplanoaula());
        diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK().setEstrategiaIdestrategia(diasemanaHasEstrategia.getEstrategia().getIdestrategia());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DiasemanaHasEstrategia persistentDiasemanaHasEstrategia = em.find(DiasemanaHasEstrategia.class, diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK());
            Diasemana diasemanaOld = persistentDiasemanaHasEstrategia.getDiasemana();
            Diasemana diasemanaNew = diasemanaHasEstrategia.getDiasemana();
            Planoaula planoaulaOld = persistentDiasemanaHasEstrategia.getPlanoaula();
            Planoaula planoaulaNew = diasemanaHasEstrategia.getPlanoaula();
            if (diasemanaNew != null) {
                diasemanaNew = em.getReference(diasemanaNew.getClass(), diasemanaNew.getIddiasemana());
                diasemanaHasEstrategia.setDiasemana(diasemanaNew);
            }
            if (planoaulaNew != null) {
                planoaulaNew = em.getReference(planoaulaNew.getClass(), planoaulaNew.getIdplanoaula());
                diasemanaHasEstrategia.setPlanoaula(planoaulaNew);
            }
            diasemanaHasEstrategia = em.merge(diasemanaHasEstrategia);
            if (diasemanaOld != null && !diasemanaOld.equals(diasemanaNew)) {
                diasemanaOld.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategia);
                diasemanaOld = em.merge(diasemanaOld);
            }
            if (diasemanaNew != null && !diasemanaNew.equals(diasemanaOld)) {
                diasemanaNew.getDiasemanaHasEstrategiaList().add(diasemanaHasEstrategia);
                diasemanaNew = em.merge(diasemanaNew);
            }
            if (planoaulaOld != null && !planoaulaOld.equals(planoaulaNew)) {
                planoaulaOld.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategia);
                planoaulaOld = em.merge(planoaulaOld);
            }
            if (planoaulaNew != null && !planoaulaNew.equals(planoaulaOld)) {
                planoaulaNew.getDiasemanaHasEstrategiaList().add(diasemanaHasEstrategia);
                planoaulaNew = em.merge(planoaulaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                DiasemanaHasEstrategiaPK id = diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK();
                if (findDiasemanaHasEstrategia(id) == null) {
                    throw new NonexistentEntityException("The diasemanaHasEstrategia with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(DiasemanaHasEstrategiaPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            DiasemanaHasEstrategia diasemanaHasEstrategia;
            try {
                diasemanaHasEstrategia = em.getReference(DiasemanaHasEstrategia.class, id);
                diasemanaHasEstrategia.getDiasemanaHasEstrategiaPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The diasemanaHasEstrategia with id " + id + " no longer exists.", enfe);
            }
            Diasemana diasemana = diasemanaHasEstrategia.getDiasemana();
            if (diasemana != null) {
                diasemana.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategia);
                diasemana = em.merge(diasemana);
            }
            Planoaula planoaula = diasemanaHasEstrategia.getPlanoaula();
            if (planoaula != null) {
                planoaula.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategia);
                planoaula = em.merge(planoaula);
            }
            em.remove(diasemanaHasEstrategia);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<DiasemanaHasEstrategia> findDiasemanaHasEstrategiaEntities() {
        return findDiasemanaHasEstrategiaEntities(true, -1, -1);
    }

    public List<DiasemanaHasEstrategia> findDiasemanaHasEstrategiaEntities(int maxResults, int firstResult) {
        return findDiasemanaHasEstrategiaEntities(false, maxResults, firstResult);
    }

    private List<DiasemanaHasEstrategia> findDiasemanaHasEstrategiaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(DiasemanaHasEstrategia.class));
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

    public DiasemanaHasEstrategia findDiasemanaHasEstrategia(DiasemanaHasEstrategiaPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(DiasemanaHasEstrategia.class, id);
        } finally {
            em.close();
        }
    }

    public int getDiasemanaHasEstrategiaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<DiasemanaHasEstrategia> rt = cq.from(DiasemanaHasEstrategia.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
