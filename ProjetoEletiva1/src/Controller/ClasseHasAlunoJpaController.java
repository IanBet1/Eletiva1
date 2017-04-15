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
import Beans.Aluno;
import Beans.ClasseHasAluno;
import Beans.ClasseHasAlunoPK;
import Controller.exceptions.NonexistentEntityException;
import Controller.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author ianbe
 */
public class ClasseHasAlunoJpaController implements Serializable {

    public ClasseHasAlunoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ClasseHasAluno classeHasAluno) throws PreexistingEntityException, Exception {
        if (classeHasAluno.getClasseHasAlunoPK() == null) {
            classeHasAluno.setClasseHasAlunoPK(new ClasseHasAlunoPK());
        }
        classeHasAluno.getClasseHasAlunoPK().setAlunoMatricula(classeHasAluno.getAluno().getMatricula());
        classeHasAluno.getClasseHasAlunoPK().setClasseIdclasse(classeHasAluno.getClasse().getIdclasse());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Classe classe = classeHasAluno.getClasse();
            if (classe != null) {
                classe = em.getReference(classe.getClass(), classe.getIdclasse());
                classeHasAluno.setClasse(classe);
            }
            Aluno aluno = classeHasAluno.getAluno();
            if (aluno != null) {
                aluno = em.getReference(aluno.getClass(), aluno.getMatricula());
                classeHasAluno.setAluno(aluno);
            }
            em.persist(classeHasAluno);
            if (classe != null) {
                classe.getClasseHasAlunoList().add(classeHasAluno);
                classe = em.merge(classe);
            }
            if (aluno != null) {
                aluno.getClasseHasAlunoList().add(classeHasAluno);
                aluno = em.merge(aluno);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findClasseHasAluno(classeHasAluno.getClasseHasAlunoPK()) != null) {
                throw new PreexistingEntityException("ClasseHasAluno " + classeHasAluno + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ClasseHasAluno classeHasAluno) throws NonexistentEntityException, Exception {
        classeHasAluno.getClasseHasAlunoPK().setAlunoMatricula(classeHasAluno.getAluno().getMatricula());
        classeHasAluno.getClasseHasAlunoPK().setClasseIdclasse(classeHasAluno.getClasse().getIdclasse());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClasseHasAluno persistentClasseHasAluno = em.find(ClasseHasAluno.class, classeHasAluno.getClasseHasAlunoPK());
            Classe classeOld = persistentClasseHasAluno.getClasse();
            Classe classeNew = classeHasAluno.getClasse();
            Aluno alunoOld = persistentClasseHasAluno.getAluno();
            Aluno alunoNew = classeHasAluno.getAluno();
            if (classeNew != null) {
                classeNew = em.getReference(classeNew.getClass(), classeNew.getIdclasse());
                classeHasAluno.setClasse(classeNew);
            }
            if (alunoNew != null) {
                alunoNew = em.getReference(alunoNew.getClass(), alunoNew.getMatricula());
                classeHasAluno.setAluno(alunoNew);
            }
            classeHasAluno = em.merge(classeHasAluno);
            if (classeOld != null && !classeOld.equals(classeNew)) {
                classeOld.getClasseHasAlunoList().remove(classeHasAluno);
                classeOld = em.merge(classeOld);
            }
            if (classeNew != null && !classeNew.equals(classeOld)) {
                classeNew.getClasseHasAlunoList().add(classeHasAluno);
                classeNew = em.merge(classeNew);
            }
            if (alunoOld != null && !alunoOld.equals(alunoNew)) {
                alunoOld.getClasseHasAlunoList().remove(classeHasAluno);
                alunoOld = em.merge(alunoOld);
            }
            if (alunoNew != null && !alunoNew.equals(alunoOld)) {
                alunoNew.getClasseHasAlunoList().add(classeHasAluno);
                alunoNew = em.merge(alunoNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ClasseHasAlunoPK id = classeHasAluno.getClasseHasAlunoPK();
                if (findClasseHasAluno(id) == null) {
                    throw new NonexistentEntityException("The classeHasAluno with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ClasseHasAlunoPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ClasseHasAluno classeHasAluno;
            try {
                classeHasAluno = em.getReference(ClasseHasAluno.class, id);
                classeHasAluno.getClasseHasAlunoPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The classeHasAluno with id " + id + " no longer exists.", enfe);
            }
            Classe classe = classeHasAluno.getClasse();
            if (classe != null) {
                classe.getClasseHasAlunoList().remove(classeHasAluno);
                classe = em.merge(classe);
            }
            Aluno aluno = classeHasAluno.getAluno();
            if (aluno != null) {
                aluno.getClasseHasAlunoList().remove(classeHasAluno);
                aluno = em.merge(aluno);
            }
            em.remove(classeHasAluno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ClasseHasAluno> findClasseHasAlunoEntities() {
        return findClasseHasAlunoEntities(true, -1, -1);
    }

    public List<ClasseHasAluno> findClasseHasAlunoEntities(int maxResults, int firstResult) {
        return findClasseHasAlunoEntities(false, maxResults, firstResult);
    }

    private List<ClasseHasAluno> findClasseHasAlunoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ClasseHasAluno.class));
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

    public ClasseHasAluno findClasseHasAluno(ClasseHasAlunoPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ClasseHasAluno.class, id);
        } finally {
            em.close();
        }
    }

    public int getClasseHasAlunoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ClasseHasAluno> rt = cq.from(ClasseHasAluno.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
