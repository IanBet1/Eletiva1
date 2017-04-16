/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Aluno;
import Beans.Classe;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Beans.ClasseHasAluno;
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
public class AlunoJpaController implements Serializable {

    public AlunoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Aluno aluno) throws PreexistingEntityException, Exception {
        if (aluno.getClasseHasAlunoList() == null) {
            aluno.setClasseHasAlunoList(new ArrayList<ClasseHasAluno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<ClasseHasAluno> attachedClasseHasAlunoList = new ArrayList<ClasseHasAluno>();
            for (ClasseHasAluno classeHasAlunoListClasseHasAlunoToAttach : aluno.getClasseHasAlunoList()) {
                classeHasAlunoListClasseHasAlunoToAttach = em.getReference(classeHasAlunoListClasseHasAlunoToAttach.getClass(), classeHasAlunoListClasseHasAlunoToAttach.getClasseHasAlunoPK());
                attachedClasseHasAlunoList.add(classeHasAlunoListClasseHasAlunoToAttach);
            }
            aluno.setClasseHasAlunoList(attachedClasseHasAlunoList);
            em.persist(aluno);
            for (ClasseHasAluno classeHasAlunoListClasseHasAluno : aluno.getClasseHasAlunoList()) {
                Aluno oldAlunoOfClasseHasAlunoListClasseHasAluno = classeHasAlunoListClasseHasAluno.getAluno();
                classeHasAlunoListClasseHasAluno.setAluno(aluno);
                classeHasAlunoListClasseHasAluno = em.merge(classeHasAlunoListClasseHasAluno);
                if (oldAlunoOfClasseHasAlunoListClasseHasAluno != null) {
                    oldAlunoOfClasseHasAlunoListClasseHasAluno.getClasseHasAlunoList().remove(classeHasAlunoListClasseHasAluno);
                    oldAlunoOfClasseHasAlunoListClasseHasAluno = em.merge(oldAlunoOfClasseHasAlunoListClasseHasAluno);
                }
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

    public void edit(Aluno aluno) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Aluno persistentAluno = em.find(Aluno.class, aluno.getMatricula());
            List<ClasseHasAluno> classeHasAlunoListOld = persistentAluno.getClasseHasAlunoList();
            List<ClasseHasAluno> classeHasAlunoListNew = aluno.getClasseHasAlunoList();
            List<String> illegalOrphanMessages = null;
            for (ClasseHasAluno classeHasAlunoListOldClasseHasAluno : classeHasAlunoListOld) {
                if (!classeHasAlunoListNew.contains(classeHasAlunoListOldClasseHasAluno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClasseHasAluno " + classeHasAlunoListOldClasseHasAluno + " since its aluno field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<ClasseHasAluno> attachedClasseHasAlunoListNew = new ArrayList<ClasseHasAluno>();
            for (ClasseHasAluno classeHasAlunoListNewClasseHasAlunoToAttach : classeHasAlunoListNew) {
                classeHasAlunoListNewClasseHasAlunoToAttach = em.getReference(classeHasAlunoListNewClasseHasAlunoToAttach.getClass(), classeHasAlunoListNewClasseHasAlunoToAttach.getClasseHasAlunoPK());
                attachedClasseHasAlunoListNew.add(classeHasAlunoListNewClasseHasAlunoToAttach);
            }
            classeHasAlunoListNew = attachedClasseHasAlunoListNew;
            aluno.setClasseHasAlunoList(classeHasAlunoListNew);
            aluno = em.merge(aluno);
            for (ClasseHasAluno classeHasAlunoListNewClasseHasAluno : classeHasAlunoListNew) {
                if (!classeHasAlunoListOld.contains(classeHasAlunoListNewClasseHasAluno)) {
                    Aluno oldAlunoOfClasseHasAlunoListNewClasseHasAluno = classeHasAlunoListNewClasseHasAluno.getAluno();
                    classeHasAlunoListNewClasseHasAluno.setAluno(aluno);
                    classeHasAlunoListNewClasseHasAluno = em.merge(classeHasAlunoListNewClasseHasAluno);
                    if (oldAlunoOfClasseHasAlunoListNewClasseHasAluno != null && !oldAlunoOfClasseHasAlunoListNewClasseHasAluno.equals(aluno)) {
                        oldAlunoOfClasseHasAlunoListNewClasseHasAluno.getClasseHasAlunoList().remove(classeHasAlunoListNewClasseHasAluno);
                        oldAlunoOfClasseHasAlunoListNewClasseHasAluno = em.merge(oldAlunoOfClasseHasAlunoListNewClasseHasAluno);
                    }
                }
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

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<ClasseHasAluno> classeHasAlunoListOrphanCheck = aluno.getClasseHasAlunoList();
            for (ClasseHasAluno classeHasAlunoListOrphanCheckClasseHasAluno : classeHasAlunoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Aluno (" + aluno + ") cannot be destroyed since the ClasseHasAluno " + classeHasAlunoListOrphanCheckClasseHasAluno + " in its classeHasAlunoList field has a non-nullable aluno field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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

    public List<Aluno> getAlunoByNomeLike(String nome) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Aluno.findByNomeLike").setParameter("nomealuno", "%" + nome + "%").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Aluno> getAlunosAtivos() {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Aluno.findAtivosSemSala").setParameter("status", true).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Aluno> getAlunoAtivosClasse(Classe c) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Aluno.findAtivosNaSala").setParameter("classe", c).setParameter("status", true).getResultList();
        } finally {
            em.close();
        }
    }

    public Aluno getAlunoDesativosClasse(Classe c, Aluno a) {
        EntityManager em = getEntityManager();
        try {
            return (Aluno) em.createNamedQuery("Aluno.findDesativosNaSala").setParameter("classe", c).setParameter("matricula", a.getMatricula()).setParameter("status", false).getSingleResult();
        } finally {
            em.close();
        }
    }

    public List<Aluno> getAlunosNessaClasse(Classe c) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Aluno.findAtivosNessaSala").setParameter("classe", c).setParameter("status", true).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Aluno> getAlunosAtivosByNome(String nome) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Aluno.findAtivosSemSalaByNome").setParameter("nome", "%"+nome+"%").setParameter("status", true).getResultList();
        } finally {
            em.close();
        }
    }

}
