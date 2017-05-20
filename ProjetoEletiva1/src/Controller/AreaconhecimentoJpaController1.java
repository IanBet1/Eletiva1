/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Areaconhecimento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Beans.Estrategia;
import java.util.ArrayList;
import java.util.List;
import Beans.Avaliacao;
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
public class AreaconhecimentoJpaController1 implements Serializable {

    public AreaconhecimentoJpaController1(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Areaconhecimento areaconhecimento) throws PreexistingEntityException, Exception {
        if (areaconhecimento.getEstrategiaList() == null) {
            areaconhecimento.setEstrategiaList(new ArrayList<Estrategia>());
        }
        if (areaconhecimento.getAvaliacaoCollection() == null) {
            areaconhecimento.setAvaliacaoCollection(new ArrayList<Avaliacao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estrategia> attachedEstrategiaList = new ArrayList<Estrategia>();
            for (Estrategia estrategiaListEstrategiaToAttach : areaconhecimento.getEstrategiaList()) {
                estrategiaListEstrategiaToAttach = em.getReference(estrategiaListEstrategiaToAttach.getClass(), estrategiaListEstrategiaToAttach.getIdestrategia());
                attachedEstrategiaList.add(estrategiaListEstrategiaToAttach);
            }
            areaconhecimento.setEstrategiaList(attachedEstrategiaList);
            Collection<Avaliacao> attachedAvaliacaoCollection = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaoCollectionAvaliacaoToAttach : areaconhecimento.getAvaliacaoCollection()) {
                avaliacaoCollectionAvaliacaoToAttach = em.getReference(avaliacaoCollectionAvaliacaoToAttach.getClass(), avaliacaoCollectionAvaliacaoToAttach.getIdavaliacao());
                attachedAvaliacaoCollection.add(avaliacaoCollectionAvaliacaoToAttach);
            }
            areaconhecimento.setAvaliacaoCollection(attachedAvaliacaoCollection);
            em.persist(areaconhecimento);
            for (Estrategia estrategiaListEstrategia : areaconhecimento.getEstrategiaList()) {
                Areaconhecimento oldAreaconhecimentoIdconhecimentoOfEstrategiaListEstrategia = estrategiaListEstrategia.getAreaconhecimentoIdconhecimento();
                estrategiaListEstrategia.setAreaconhecimentoIdconhecimento(areaconhecimento);
                estrategiaListEstrategia = em.merge(estrategiaListEstrategia);
                if (oldAreaconhecimentoIdconhecimentoOfEstrategiaListEstrategia != null) {
                    oldAreaconhecimentoIdconhecimentoOfEstrategiaListEstrategia.getEstrategiaList().remove(estrategiaListEstrategia);
                    oldAreaconhecimentoIdconhecimentoOfEstrategiaListEstrategia = em.merge(oldAreaconhecimentoIdconhecimentoOfEstrategiaListEstrategia);
                }
            }
            for (Avaliacao avaliacaoCollectionAvaliacao : areaconhecimento.getAvaliacaoCollection()) {
                Areaconhecimento oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionAvaliacao = avaliacaoCollectionAvaliacao.getAreaconhecimentoIdareaconhecimento();
                avaliacaoCollectionAvaliacao.setAreaconhecimentoIdareaconhecimento(areaconhecimento);
                avaliacaoCollectionAvaliacao = em.merge(avaliacaoCollectionAvaliacao);
                if (oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionAvaliacao != null) {
                    oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionAvaliacao.getAvaliacaoCollection().remove(avaliacaoCollectionAvaliacao);
                    oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionAvaliacao = em.merge(oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionAvaliacao);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAreaconhecimento(areaconhecimento.getIdconhecimento()) != null) {
                throw new PreexistingEntityException("Areaconhecimento " + areaconhecimento + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Areaconhecimento areaconhecimento) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areaconhecimento persistentAreaconhecimento = em.find(Areaconhecimento.class, areaconhecimento.getIdconhecimento());
            List<Estrategia> estrategiaListOld = persistentAreaconhecimento.getEstrategiaList();
            List<Estrategia> estrategiaListNew = areaconhecimento.getEstrategiaList();
            Collection<Avaliacao> avaliacaoCollectionOld = persistentAreaconhecimento.getAvaliacaoCollection();
            Collection<Avaliacao> avaliacaoCollectionNew = areaconhecimento.getAvaliacaoCollection();
            List<String> illegalOrphanMessages = null;
            for (Estrategia estrategiaListOldEstrategia : estrategiaListOld) {
                if (!estrategiaListNew.contains(estrategiaListOldEstrategia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estrategia " + estrategiaListOldEstrategia + " since its areaconhecimentoIdconhecimento field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estrategia> attachedEstrategiaListNew = new ArrayList<Estrategia>();
            for (Estrategia estrategiaListNewEstrategiaToAttach : estrategiaListNew) {
                estrategiaListNewEstrategiaToAttach = em.getReference(estrategiaListNewEstrategiaToAttach.getClass(), estrategiaListNewEstrategiaToAttach.getIdestrategia());
                attachedEstrategiaListNew.add(estrategiaListNewEstrategiaToAttach);
            }
            estrategiaListNew = attachedEstrategiaListNew;
            areaconhecimento.setEstrategiaList(estrategiaListNew);
            Collection<Avaliacao> attachedAvaliacaoCollectionNew = new ArrayList<Avaliacao>();
            for (Avaliacao avaliacaoCollectionNewAvaliacaoToAttach : avaliacaoCollectionNew) {
                avaliacaoCollectionNewAvaliacaoToAttach = em.getReference(avaliacaoCollectionNewAvaliacaoToAttach.getClass(), avaliacaoCollectionNewAvaliacaoToAttach.getIdavaliacao());
                attachedAvaliacaoCollectionNew.add(avaliacaoCollectionNewAvaliacaoToAttach);
            }
            avaliacaoCollectionNew = attachedAvaliacaoCollectionNew;
            areaconhecimento.setAvaliacaoCollection(avaliacaoCollectionNew);
            areaconhecimento = em.merge(areaconhecimento);
            for (Estrategia estrategiaListNewEstrategia : estrategiaListNew) {
                if (!estrategiaListOld.contains(estrategiaListNewEstrategia)) {
                    Areaconhecimento oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia = estrategiaListNewEstrategia.getAreaconhecimentoIdconhecimento();
                    estrategiaListNewEstrategia.setAreaconhecimentoIdconhecimento(areaconhecimento);
                    estrategiaListNewEstrategia = em.merge(estrategiaListNewEstrategia);
                    if (oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia != null && !oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia.equals(areaconhecimento)) {
                        oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia.getEstrategiaList().remove(estrategiaListNewEstrategia);
                        oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia = em.merge(oldAreaconhecimentoIdconhecimentoOfEstrategiaListNewEstrategia);
                    }
                }
            }
            for (Avaliacao avaliacaoCollectionOldAvaliacao : avaliacaoCollectionOld) {
                if (!avaliacaoCollectionNew.contains(avaliacaoCollectionOldAvaliacao)) {
                    avaliacaoCollectionOldAvaliacao.setAreaconhecimentoIdareaconhecimento(null);
                    avaliacaoCollectionOldAvaliacao = em.merge(avaliacaoCollectionOldAvaliacao);
                }
            }
            for (Avaliacao avaliacaoCollectionNewAvaliacao : avaliacaoCollectionNew) {
                if (!avaliacaoCollectionOld.contains(avaliacaoCollectionNewAvaliacao)) {
                    Areaconhecimento oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao = avaliacaoCollectionNewAvaliacao.getAreaconhecimentoIdareaconhecimento();
                    avaliacaoCollectionNewAvaliacao.setAreaconhecimentoIdareaconhecimento(areaconhecimento);
                    avaliacaoCollectionNewAvaliacao = em.merge(avaliacaoCollectionNewAvaliacao);
                    if (oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao != null && !oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao.equals(areaconhecimento)) {
                        oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao.getAvaliacaoCollection().remove(avaliacaoCollectionNewAvaliacao);
                        oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao = em.merge(oldAreaconhecimentoIdareaconhecimentoOfAvaliacaoCollectionNewAvaliacao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = areaconhecimento.getIdconhecimento();
                if (findAreaconhecimento(id) == null) {
                    throw new NonexistentEntityException("The areaconhecimento with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Areaconhecimento areaconhecimento;
            try {
                areaconhecimento = em.getReference(Areaconhecimento.class, id);
                areaconhecimento.getIdconhecimento();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The areaconhecimento with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estrategia> estrategiaListOrphanCheck = areaconhecimento.getEstrategiaList();
            for (Estrategia estrategiaListOrphanCheckEstrategia : estrategiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Areaconhecimento (" + areaconhecimento + ") cannot be destroyed since the Estrategia " + estrategiaListOrphanCheckEstrategia + " in its estrategiaList field has a non-nullable areaconhecimentoIdconhecimento field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Avaliacao> avaliacaoCollection = areaconhecimento.getAvaliacaoCollection();
            for (Avaliacao avaliacaoCollectionAvaliacao : avaliacaoCollection) {
                avaliacaoCollectionAvaliacao.setAreaconhecimentoIdareaconhecimento(null);
                avaliacaoCollectionAvaliacao = em.merge(avaliacaoCollectionAvaliacao);
            }
            em.remove(areaconhecimento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Areaconhecimento> findAreaconhecimentoEntities() {
        return findAreaconhecimentoEntities(true, -1, -1);
    }

    public List<Areaconhecimento> findAreaconhecimentoEntities(int maxResults, int firstResult) {
        return findAreaconhecimentoEntities(false, maxResults, firstResult);
    }

    private List<Areaconhecimento> findAreaconhecimentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Areaconhecimento.class));
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

    public Areaconhecimento findAreaconhecimento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Areaconhecimento.class, id);
        } finally {
            em.close();
        }
    }

    public int getAreaconhecimentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Areaconhecimento> rt = cq.from(Areaconhecimento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
