/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Classe;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Beans.Usuario;
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
public class ClasseJpaController implements Serializable {

    public ClasseJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Classe classe) throws PreexistingEntityException, Exception {
        if (classe.getClasseHasAlunoList() == null) {
            classe.setClasseHasAlunoList(new ArrayList<ClasseHasAluno>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario professor = classe.getProfessor();
            if (professor != null) {
                professor = em.getReference(professor.getClass(), professor.getLogin());
                classe.setProfessor(professor);
            }
            List<ClasseHasAluno> attachedClasseHasAlunoList = new ArrayList<ClasseHasAluno>();
            for (ClasseHasAluno classeHasAlunoListClasseHasAlunoToAttach : classe.getClasseHasAlunoList()) {
                classeHasAlunoListClasseHasAlunoToAttach = em.getReference(classeHasAlunoListClasseHasAlunoToAttach.getClass(), classeHasAlunoListClasseHasAlunoToAttach.getClasseHasAlunoPK());
                attachedClasseHasAlunoList.add(classeHasAlunoListClasseHasAlunoToAttach);
            }
            classe.setClasseHasAlunoList(attachedClasseHasAlunoList);
            em.persist(classe);
            if (professor != null) {
                professor.getClasseList().add(classe);
                professor = em.merge(professor);
            }
            for (ClasseHasAluno classeHasAlunoListClasseHasAluno : classe.getClasseHasAlunoList()) {
                Classe oldClasseOfClasseHasAlunoListClasseHasAluno = classeHasAlunoListClasseHasAluno.getClasse();
                classeHasAlunoListClasseHasAluno.setClasse(classe);
                classeHasAlunoListClasseHasAluno = em.merge(classeHasAlunoListClasseHasAluno);
                if (oldClasseOfClasseHasAlunoListClasseHasAluno != null) {
                    oldClasseOfClasseHasAlunoListClasseHasAluno.getClasseHasAlunoList().remove(classeHasAlunoListClasseHasAluno);
                    oldClasseOfClasseHasAlunoListClasseHasAluno = em.merge(oldClasseOfClasseHasAlunoListClasseHasAluno);
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
            Usuario professorOld = persistentClasse.getProfessor();
            Usuario professorNew = classe.getProfessor();
            List<ClasseHasAluno> classeHasAlunoListOld = persistentClasse.getClasseHasAlunoList();
            List<ClasseHasAluno> classeHasAlunoListNew = classe.getClasseHasAlunoList();
            List<String> illegalOrphanMessages = null;
            for (ClasseHasAluno classeHasAlunoListOldClasseHasAluno : classeHasAlunoListOld) {
                if (!classeHasAlunoListNew.contains(classeHasAlunoListOldClasseHasAluno)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ClasseHasAluno " + classeHasAlunoListOldClasseHasAluno + " since its classe field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (professorNew != null) {
                professorNew = em.getReference(professorNew.getClass(), professorNew.getLogin());
                classe.setProfessor(professorNew);
            }
            List<ClasseHasAluno> attachedClasseHasAlunoListNew = new ArrayList<ClasseHasAluno>();
            for (ClasseHasAluno classeHasAlunoListNewClasseHasAlunoToAttach : classeHasAlunoListNew) {
                classeHasAlunoListNewClasseHasAlunoToAttach = em.getReference(classeHasAlunoListNewClasseHasAlunoToAttach.getClass(), classeHasAlunoListNewClasseHasAlunoToAttach.getClasseHasAlunoPK());
                attachedClasseHasAlunoListNew.add(classeHasAlunoListNewClasseHasAlunoToAttach);
            }
            classeHasAlunoListNew = attachedClasseHasAlunoListNew;
            classe.setClasseHasAlunoList(classeHasAlunoListNew);
            classe = em.merge(classe);
            if (professorOld != null && !professorOld.equals(professorNew)) {
                professorOld.getClasseList().remove(classe);
                professorOld = em.merge(professorOld);
            }
            if (professorNew != null && !professorNew.equals(professorOld)) {
                professorNew.getClasseList().add(classe);
                professorNew = em.merge(professorNew);
            }
            for (ClasseHasAluno classeHasAlunoListNewClasseHasAluno : classeHasAlunoListNew) {
                if (!classeHasAlunoListOld.contains(classeHasAlunoListNewClasseHasAluno)) {
                    Classe oldClasseOfClasseHasAlunoListNewClasseHasAluno = classeHasAlunoListNewClasseHasAluno.getClasse();
                    classeHasAlunoListNewClasseHasAluno.setClasse(classe);
                    classeHasAlunoListNewClasseHasAluno = em.merge(classeHasAlunoListNewClasseHasAluno);
                    if (oldClasseOfClasseHasAlunoListNewClasseHasAluno != null && !oldClasseOfClasseHasAlunoListNewClasseHasAluno.equals(classe)) {
                        oldClasseOfClasseHasAlunoListNewClasseHasAluno.getClasseHasAlunoList().remove(classeHasAlunoListNewClasseHasAluno);
                        oldClasseOfClasseHasAlunoListNewClasseHasAluno = em.merge(oldClasseOfClasseHasAlunoListNewClasseHasAluno);
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
            List<ClasseHasAluno> classeHasAlunoListOrphanCheck = classe.getClasseHasAlunoList();
            for (ClasseHasAluno classeHasAlunoListOrphanCheckClasseHasAluno : classeHasAlunoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Classe (" + classe + ") cannot be destroyed since the ClasseHasAluno " + classeHasAlunoListOrphanCheckClasseHasAluno + " in its classeHasAlunoList field has a non-nullable classe field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Usuario professor = classe.getProfessor();
            if (professor != null) {
                professor.getClasseList().remove(classe);
                professor = em.merge(professor);
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

    public List<Classe> getClasseByAno(int anoclasse) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Classe.findByAnoclasse").setParameter("anoclasse", anoclasse).getResultList();
        } finally {
            em.close();
        }
    }

    public List<Classe> getClasseByPeriodo(String periodo) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Classe.findByPeriodo").setParameter("periodo", "%" + periodo + "%").getResultList();
        } finally {
            em.close();
        }
    }

    public List<Classe> getClasseByProfessor(String professor) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Classe.findByProfessor").setParameter("professor", "%" + professor + "%").getResultList();
        } finally {
            em.close();
        }
    }
}
