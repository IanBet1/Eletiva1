/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Aluno;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Beans.Classe;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ianbe
 */
public class AlunoJpaController implements Serializable {

    public AlunoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aluno aluno) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe classeIdclasse = aluno.getClasseIdclasse();
            if (classeIdclasse != null) {
                classeIdclasse = em.getReference(classeIdclasse.getClass(), classeIdclasse.getIdclasse());
                aluno.setClasseIdclasse(classeIdclasse);
            }
            em.persist(aluno);
            if (classeIdclasse != null) {
                classeIdclasse.getAlunoList().add(aluno);
                classeIdclasse = em.merge(classeIdclasse);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAluno(aluno.getMatricula()) != null) {
                throw new PreexistingEntityException("Aluno " + aluno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Aluno aluno) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno persistentAluno = em.find(Aluno.class, aluno.getMatricula());
            Classe classeIdclasseOld = persistentAluno.getClasseIdclasse();
            Classe classeIdclasseNew = aluno.getClasseIdclasse();
            if (classeIdclasseNew != null) {
                classeIdclasseNew = em.getReference(classeIdclasseNew.getClass(), classeIdclasseNew.getIdclasse());
                aluno.setClasseIdclasse(classeIdclasseNew);
            }
            aluno = em.merge(aluno);
            if (classeIdclasseOld != null && !classeIdclasseOld.equals(classeIdclasseNew)) {
                classeIdclasseOld.getAlunoList().remove(aluno);
                classeIdclasseOld = em.merge(classeIdclasseOld);
            }
            if (classeIdclasseNew != null && !classeIdclasseNew.equals(classeIdclasseOld)) {
                classeIdclasseNew.getAlunoList().add(aluno);
                classeIdclasseNew = em.merge(classeIdclasseNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = aluno.getMatricula();
                if (findAluno(id) == null) {
                    throw new NonexistentEntityException("The aluno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno aluno;
            try {
                aluno = em.getReference(Aluno.class, id);
                aluno.getMatricula();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The aluno with id " + id + " no longer exists.", enfe);
            }
            Classe classeIdclasse = aluno.getClasseIdclasse();
            if (classeIdclasse != null) {
                classeIdclasse.getAlunoList().remove(aluno);
                classeIdclasse = em.merge(classeIdclasse);
            }
            em.remove(aluno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Aluno> findAlunoEntities() {
        return findAlunoEntities(true, -1, -1);
    }

    public List<Aluno> findAlunoEntities(int maxResults, int firstResult) {
        return findAlunoEntities(false, maxResults, firstResult);
    }

    private List<Aluno> findAlunoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Aluno.class));
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

    public Aluno findAluno(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Aluno.class, id);
        } finally {
            em.close();
        }
    }

    public int getAlunoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Aluno> rt = cq.from(Aluno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
