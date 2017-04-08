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
import Beans.Aluno;
import Beans.Classe;
import Controller.exceptions.IllegalOrphanException;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ianbe
 */
public class ClasseJpaController implements Serializable {

    public ClasseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Classe classe) throws PreexistingEntityException, Exception {
        if (classe.getAlunoList() == null) {
            classe.setAlunoList(new ArrayList<Aluno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Aluno> attachedAlunoList = new ArrayList<Aluno>();
            for (Aluno alunoListAlunoToAttach : classe.getAlunoList()) {
                alunoListAlunoToAttach = em.getReference(alunoListAlunoToAttach.getClass(), alunoListAlunoToAttach.getMatricula());
                attachedAlunoList.add(alunoListAlunoToAttach);
            }
            classe.setAlunoList(attachedAlunoList);
            em.persist(classe);
            for (Aluno alunoListAluno : classe.getAlunoList()) {
                Classe oldClasseIdclasseOfAlunoListAluno = alunoListAluno.getClasseIdclasse();
                alunoListAluno.setClasseIdclasse(classe);
                alunoListAluno = em.merge(alunoListAluno);
                if (oldClasseIdclasseOfAlunoListAluno != null) {
                    oldClasseIdclasseOfAlunoListAluno.getAlunoList().remove(alunoListAluno);
                    oldClasseIdclasseOfAlunoListAluno = em.merge(oldClasseIdclasseOfAlunoListAluno);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClasse(classe.getIdclasse()) != null) {
                throw new PreexistingEntityException("Classe " + classe + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Classe classe) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe persistentClasse = em.find(Classe.class, classe.getIdclasse());
            List<Aluno> alunoListOld = persistentClasse.getAlunoList();
            List<Aluno> alunoListNew = classe.getAlunoList();
            List<String> illegalOrphanMessages = null;
            for (Aluno alunoListOldAluno : alunoListOld) {
                if (!alunoListNew.contains(alunoListOldAluno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Aluno " + alunoListOldAluno + " since its classeIdclasse field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Aluno> attachedAlunoListNew = new ArrayList<Aluno>();
            for (Aluno alunoListNewAlunoToAttach : alunoListNew) {
                alunoListNewAlunoToAttach = em.getReference(alunoListNewAlunoToAttach.getClass(), alunoListNewAlunoToAttach.getMatricula());
                attachedAlunoListNew.add(alunoListNewAlunoToAttach);
            }
            alunoListNew = attachedAlunoListNew;
            classe.setAlunoList(alunoListNew);
            classe = em.merge(classe);
            for (Aluno alunoListNewAluno : alunoListNew) {
                if (!alunoListOld.contains(alunoListNewAluno)) {
                    Classe oldClasseIdclasseOfAlunoListNewAluno = alunoListNewAluno.getClasseIdclasse();
                    alunoListNewAluno.setClasseIdclasse(classe);
                    alunoListNewAluno = em.merge(alunoListNewAluno);
                    if (oldClasseIdclasseOfAlunoListNewAluno != null && !oldClasseIdclasseOfAlunoListNewAluno.equals(classe)) {
                        oldClasseIdclasseOfAlunoListNewAluno.getAlunoList().remove(alunoListNewAluno);
                        oldClasseIdclasseOfAlunoListNewAluno = em.merge(oldClasseIdclasseOfAlunoListNewAluno);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = classe.getIdclasse();
                if (findClasse(id) == null) {
                    throw new NonexistentEntityException("The classe with id " + id + " no longer exists.");
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
            Classe classe;
            try {
                classe = em.getReference(Classe.class, id);
                classe.getIdclasse();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classe with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Aluno> alunoListOrphanCheck = classe.getAlunoList();
            for (Aluno alunoListOrphanCheckAluno : alunoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Classe (" + classe + ") cannot be destroyed since the Aluno " + alunoListOrphanCheckAluno + " in its alunoList field has a non-nullable classeIdclasse field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(classe);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Classe> findClasseEntities() {
        return findClasseEntities(true, -1, -1);
    }

    public List<Classe> findClasseEntities(int maxResults, int firstResult) {
        return findClasseEntities(false, maxResults, firstResult);
    }

    private List<Classe> findClasseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Classe.class));
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

    public Classe findClasse(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Classe.class, id);
        } finally {
            em.close();
        }
    }

    public int getClasseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Classe> rt = cq.from(Classe.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
