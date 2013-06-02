/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.controller;

import ar.edu.untdf.catalogo.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.catalogo.modelo.OrdenCliente;

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
 * @author Matias
 */
public class OrdenClienteJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrdenClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenCliente ordenCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ordenCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenCliente ordenCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ordenCliente = em.merge(ordenCliente);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = (long) ordenCliente.getIdorden();
                if (findOrdenCliente(id) == null) {
                    throw new NonexistentEntityException("The ordenCliente with id " + id + " no longer exists.");
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
            OrdenCliente ordenCliente;
            try {
                ordenCliente = em.getReference(OrdenCliente.class, id);
                ordenCliente.getIdorden();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ordenCliente with id " + id + " no longer exists.", enfe);
            }
            em.remove(ordenCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenCliente> findOrdenClienteEntities() {
        return findOrdenClienteEntities(true, -1, -1);
    }

    public List<OrdenCliente> findOrdenClienteEntities(int maxResults, int firstResult) {
        return findOrdenClienteEntities(false, maxResults, firstResult);
    }

    private List<OrdenCliente> findOrdenClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenCliente.class));
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

    public OrdenCliente findOrdenCliente(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenCliente> rt = cq.from(OrdenCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
