/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.controller;

import ar.edu.untdf.catalogo.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.catalogo.modelo.Usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.eclipse.persistence.expressions.Expression;

/**
 *
 * @author Matias
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
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
            usuario = em.merge(usuario);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                long id = usuario.getIdusuario();
                if (findUsuario((int) id) == null) {
                    throw new NonexistentEntityException("The Usuario with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdusuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Usuario with id " + id + " no longer exists.", enfe);
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

    public Usuario findUsuario(int idusuario) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, idusuario);
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

	public Usuario findUsuarioString(String text) {
		 EntityManager em = getEntityManager();
	        try {
	            return em.find(Usuario.class, text);
	        } finally {
	            em.close();
	        }
	}

	   
	public Usuario validarCredencial(String usuariologin, String passwordlogin) throws Exception {
        Usuario usuario = null;

        try{
            // Validacion de contraseña antes de consultar lo demás
            validaPassword(passwordlogin);

            // Correr validacion de usuario
            usuario = validaUsuario(usuariologin);

            // Si hay usuario, y no coincide el password, falla
            if (!usuario.getPassword().equals(passwordlogin)){
                throw new Exception("No se autentico el usuario " + usuario.getUsuario());
            }
            return usuario;
        } catch(Exception e){
            throw new Exception(e.getMessage());
        }
    }
	
	public Usuario validaUsuario(String usuario) throws Exception{
        if (usuario == null) usuario = "";
        usuario = usuario.trim();
        if ("".equals(usuario))
            throw new Exception("Usuario no puede estar vacío");

        try{
            // Esto tambien tira su excepción si no encuentra
            Usuario user = findUsuarioPorLogin(usuario);
            return user;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    public void validaPassword(String password) throws Exception{
        if ("".equals(password))
            throw new Exception("Contraseña no puede estar vacía");
    }
    
    public Usuario findUsuarioPorLogin(String usuarioLogin) throws Exception {
        EntityManager em = getEntityManager();
        Usuario user = null;
        try {
            Query query = em.createNamedQuery("Usuario.findByUsuario");
            query.setParameter("usuario", usuarioLogin);

            return (Usuario) query.getSingleResult();
        } catch (NoResultException ne){
            throw new Exception("No se encontró Usuario: " + usuarioLogin);
        } catch (NonUniqueResultException ue){
            throw new Exception("La conincidencia para el Usuario: " + usuarioLogin + ", no es única");
        } finally {
            em.close();
        }
    }
    
 // Busqueda Autocompletar x Usuario
    public List<Usuario> findUsuarioListByUser(String usuario, String tipoUsuario) {
        EntityManager em = getEntityManager();
        try {
            usuario = usuario.trim();
            // No buscar nada si está vacío
            if (usuario.equals("")){
                return new ArrayList<Usuario>();
            }
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE UPPER(u.usuario) " +
                                         "LIKE '%" + usuario.toUpperCase() + "%' " +
                                         "AND UPPER(u.tipousuario) LIKE '%" + tipoUsuario.toUpperCase() + "%' " +
                                         "ORDER BY u.usuario");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Busqueda Autocompletar x Nombre
    public List<Usuario> findUsuarioListByName(String nombre, String tipoUsuario) {
        EntityManager em = getEntityManager();
        try {
            // No buscar nada si está vacío
            if (nombre.equals("")){
                return new ArrayList<Usuario>();
            }
            nombre = nombre.trim();
            Query query = em.createQuery("SELECT u FROM Usuario u WHERE CONCAT(CONCAT(UPPER(u.nombre), ' '), " +
                                         "UPPER(u.apellido)) LIKE '%" + nombre.toUpperCase() + "%' " +
                                         " AND UPPER(u.tipousuario) LIKE '%" + tipoUsuario.toUpperCase() + "%' " +
                                         "ORDER BY u.nombre");
            return query.getResultList();
        } finally {
            em.close();
        }
    }
}
