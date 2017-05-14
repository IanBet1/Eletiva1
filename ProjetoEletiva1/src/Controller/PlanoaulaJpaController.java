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
import Beans.Usuario;
import Beans.DiasemanaHasEstrategia;
import Beans.Planoaula;
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
public class PlanoaulaJpaController implements Serializable {

    public PlanoaulaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Planoaula planoaula) throws PreexistingEntityException, Exception {
        if (planoaula.getDiasemanaHasEstrategiaList() == null) {
            planoaula.setDiasemanaHasEstrategiaList(new ArrayList<DiasemanaHasEstrategia>());
        }
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
            List<DiasemanaHasEstrategia> attachedDiasemanaHasEstrategiaList = new ArrayList<DiasemanaHasEstrategia>();
            for (DiasemanaHasEstrategia diasemanaHasEstrategiaListDiasemanaHasEstrategiaToAttach : planoaula.getDiasemanaHasEstrategiaList()) {
                diasemanaHasEstrategiaListDiasemanaHasEstrategiaToAttach = em.getReference(diasemanaHasEstrategiaListDiasemanaHasEstrategiaToAttach.getClass(), diasemanaHasEstrategiaListDiasemanaHasEstrategiaToAttach.getDiasemanaHasEstrategiaPK());
                attachedDiasemanaHasEstrategiaList.add(diasemanaHasEstrategiaListDiasemanaHasEstrategiaToAttach);
            }
            planoaula.setDiasemanaHasEstrategiaList(attachedDiasemanaHasEstrategiaList);
            em.persist(planoaula);
            if (classeIdclasse != null) {
                classeIdclasse.getPlanoaulaList().add(planoaula);
                classeIdclasse = em.merge(classeIdclasse);
            }
            if (usuarioLogin != null) {
                usuarioLogin.getPlanoaulaList().add(planoaula);
                usuarioLogin = em.merge(usuarioLogin);
            }
            for (DiasemanaHasEstrategia diasemanaHasEstrategiaListDiasemanaHasEstrategia : planoaula.getDiasemanaHasEstrategiaList()) {
                Planoaula oldPlanoaulaOfDiasemanaHasEstrategiaListDiasemanaHasEstrategia = diasemanaHasEstrategiaListDiasemanaHasEstrategia.getPlanoaula();
                diasemanaHasEstrategiaListDiasemanaHasEstrategia.setPlanoaula(planoaula);
                diasemanaHasEstrategiaListDiasemanaHasEstrategia = em.merge(diasemanaHasEstrategiaListDiasemanaHasEstrategia);
                if (oldPlanoaulaOfDiasemanaHasEstrategiaListDiasemanaHasEstrategia != null) {
                    oldPlanoaulaOfDiasemanaHasEstrategiaListDiasemanaHasEstrategia.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategiaListDiasemanaHasEstrategia);
                    oldPlanoaulaOfDiasemanaHasEstrategiaListDiasemanaHasEstrategia = em.merge(oldPlanoaulaOfDiasemanaHasEstrategiaListDiasemanaHasEstrategia);
                }
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

    public void edit(Planoaula planoaula) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Planoaula persistentPlanoaula = em.find(Planoaula.class, planoaula.getIdplanoaula());
            Classe classeIdclasseOld = persistentPlanoaula.getClasseIdclasse();
            Classe classeIdclasseNew = planoaula.getClasseIdclasse();
            Usuario usuarioLoginOld = persistentPlanoaula.getUsuarioLogin();
            Usuario usuarioLoginNew = planoaula.getUsuarioLogin();
            List<DiasemanaHasEstrategia> diasemanaHasEstrategiaListOld = persistentPlanoaula.getDiasemanaHasEstrategiaList();
            List<DiasemanaHasEstrategia> diasemanaHasEstrategiaListNew = planoaula.getDiasemanaHasEstrategiaList();
            List<String> illegalOrphanMessages = null;
            /*for (DiasemanaHasEstrategia diasemanaHasEstrategiaListOldDiasemanaHasEstrategia : diasemanaHasEstrategiaListOld) {
                if (!diasemanaHasEstrategiaListNew.contains(diasemanaHasEstrategiaListOldDiasemanaHasEstrategia)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain DiasemanaHasEstrategia " + diasemanaHasEstrategiaListOldDiasemanaHasEstrategia + " since its planoaula field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }*/
            if (classeIdclasseNew != null) {
                classeIdclasseNew = em.getReference(classeIdclasseNew.getClass(), classeIdclasseNew.getIdclasse());
                planoaula.setClasseIdclasse(classeIdclasseNew);
            }
            if (usuarioLoginNew != null) {
                usuarioLoginNew = em.getReference(usuarioLoginNew.getClass(), usuarioLoginNew.getLogin());
                planoaula.setUsuarioLogin(usuarioLoginNew);
            }
            List<DiasemanaHasEstrategia> attachedDiasemanaHasEstrategiaListNew = new ArrayList<DiasemanaHasEstrategia>();
            /*for (DiasemanaHasEstrategia diasemanaHasEstrategiaListNewDiasemanaHasEstrategiaToAttach : diasemanaHasEstrategiaListNew) {
                diasemanaHasEstrategiaListNewDiasemanaHasEstrategiaToAttach = em.getReference(diasemanaHasEstrategiaListNewDiasemanaHasEstrategiaToAttach.getClass(), diasemanaHasEstrategiaListNewDiasemanaHasEstrategiaToAttach.getDiasemanaHasEstrategiaPK());
                attachedDiasemanaHasEstrategiaListNew.add(diasemanaHasEstrategiaListNewDiasemanaHasEstrategiaToAttach);
            }*/
            diasemanaHasEstrategiaListNew = attachedDiasemanaHasEstrategiaListNew;
            planoaula.setDiasemanaHasEstrategiaList(diasemanaHasEstrategiaListNew);
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
            for (DiasemanaHasEstrategia diasemanaHasEstrategiaListNewDiasemanaHasEstrategia : diasemanaHasEstrategiaListNew) {
                if (!diasemanaHasEstrategiaListOld.contains(diasemanaHasEstrategiaListNewDiasemanaHasEstrategia)) {
                    Planoaula oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia = diasemanaHasEstrategiaListNewDiasemanaHasEstrategia.getPlanoaula();
                    diasemanaHasEstrategiaListNewDiasemanaHasEstrategia.setPlanoaula(planoaula);
                    diasemanaHasEstrategiaListNewDiasemanaHasEstrategia = em.merge(diasemanaHasEstrategiaListNewDiasemanaHasEstrategia);
                    if (oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia != null && !oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia.equals(planoaula)) {
                        oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia.getDiasemanaHasEstrategiaList().remove(diasemanaHasEstrategiaListNewDiasemanaHasEstrategia);
                        oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia = em.merge(oldPlanoaulaOfDiasemanaHasEstrategiaListNewDiasemanaHasEstrategia);
                    }
                }
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

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
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
            List<String> illegalOrphanMessages = null;
            List<DiasemanaHasEstrategia> diasemanaHasEstrategiaListOrphanCheck = planoaula.getDiasemanaHasEstrategiaList();
            for (DiasemanaHasEstrategia diasemanaHasEstrategiaListOrphanCheckDiasemanaHasEstrategia : diasemanaHasEstrategiaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Planoaula (" + planoaula + ") cannot be destroyed since the DiasemanaHasEstrategia " + diasemanaHasEstrategiaListOrphanCheckDiasemanaHasEstrategia + " in its diasemanaHasEstrategiaList field has a non-nullable planoaula field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
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
    
    public Planoaula getPlano(Planoaula p){
        EntityManager em = getEntityManager();
        try {
            return (Planoaula) em.createNamedQuery("Planoaula.findByPlanoAula").setParameter("datainicio", p.getDatainicio()).setParameter("datafim",p.getDatafim()).setParameter("login", p.getUsuarioLogin().getLogin()).getSingleResult();
        } finally {
            em.close();
        }
    }
    
    public List<Planoaula> getPlanoProfessor(Planoaula p){
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Planoaula.findByPlanoProfessor").setParameter("login", p.getUsuarioLogin().getLogin()).getResultList();
        } finally {
            em.close();
        }
    }
    
    public List<Planoaula> getPlano1(Planoaula p){
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Planoaula.findByPlanoAula").setParameter("datainicio", p.getDatainicio()).setParameter("datafim",p.getDatafim()).setParameter("login", p.getUsuarioLogin().getLogin()).getResultList();
        } finally {
            em.close();
        }
    }
    
}
