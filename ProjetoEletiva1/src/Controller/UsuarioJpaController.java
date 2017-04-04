/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Beans.Usuario;
import Beans.Categoria;
import Beans.exceptions.NonexistentEntityException;
import Beans.exceptions.PreexistingEntityException;
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
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Categoria categoriaIdcategoria = usuario.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria = em.getReference(categoriaIdcategoria.getClass(), categoriaIdcategoria.getIdcategoria());
                usuario.setCategoriaIdcategoria(categoriaIdcategoria);
            }
            em.persist(usuario);
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getUsuarioList().add(usuario);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getLogin()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getLogin());
            Categoria categoriaIdcategoriaOld = persistentUsuario.getCategoriaIdcategoria();
            Categoria categoriaIdcategoriaNew = usuario.getCategoriaIdcategoria();
            if (categoriaIdcategoriaNew != null) {
                categoriaIdcategoriaNew = em.getReference(categoriaIdcategoriaNew.getClass(), categoriaIdcategoriaNew.getIdcategoria());
                usuario.setCategoriaIdcategoria(categoriaIdcategoriaNew);
            }
            usuario = em.merge(usuario);
            if (categoriaIdcategoriaOld != null && !categoriaIdcategoriaOld.equals(categoriaIdcategoriaNew)) {
                categoriaIdcategoriaOld.getUsuarioList().remove(usuario);
                categoriaIdcategoriaOld = em.merge(categoriaIdcategoriaOld);
            }
            if (categoriaIdcategoriaNew != null && !categoriaIdcategoriaNew.equals(categoriaIdcategoriaOld)) {
                categoriaIdcategoriaNew.getUsuarioList().add(usuario);
                categoriaIdcategoriaNew = em.merge(categoriaIdcategoriaNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuario.getLogin();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getLogin();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            Categoria categoriaIdcategoria = usuario.getCategoriaIdcategoria();
            if (categoriaIdcategoria != null) {
                categoriaIdcategoria.getUsuarioList().remove(usuario);
                categoriaIdcategoria = em.merge(categoriaIdcategoria);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public List<Usuario> getFuncionarioByNomeLike(String nome) {
        EntityManager em = getEntityManager();
        try {
            return em.createNamedQuery("Usuario.findByNomeLike").setParameter("nome", "%" + nome + "%").getResultList();
        } finally {
            em.close();
        }
            
    }
    
    public Usuario login(Usuario u)
    {
        EntityManager em = getEntityManager();
        try {
            return (Usuario)em.createNamedQuery("Usuario.login").setParameter("login", u.getLogin()).setParameter("senha", u.getSenha()).getSingleResult();
        } finally {
            em.close();
        }
           
    }
    
   
}
