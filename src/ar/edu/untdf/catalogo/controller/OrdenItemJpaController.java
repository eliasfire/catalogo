/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.edu.untdf.catalogo.controller;

import ar.edu.untdf.catalogo.controller.exceptions.NonexistentEntityException;
import ar.edu.untdf.catalogo.modelo.OrdenItem;

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
public class OrdenItemJpaController implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OrdenItemJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(OrdenItem ordenItem) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(ordenItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(OrdenItem ordenItem) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ordenItem = em.merge(ordenItem);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = (long) ordenItem.getIdordenItems();
                if (findOrdenItem(id) == null) {
                    throw new NonexistentEntityException("The cliente with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(int codigo) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            OrdenItem ordenItem;
            try {
            	ordenItem = em.getReference(OrdenItem.class, codigo);
            	ordenItem.getIdordenItems();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cliente with id " + codigo + " no longer exists.", enfe);
            }
            em.remove(ordenItem);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<OrdenItem> findOrdenItemEntities() {
        return findOrdenItemEntities(true, -1, -1);
    }

    public List<OrdenItem> findOrdenItemEntities(int maxResults, int firstResult) {
        return findOrdenItemEntities(false, maxResults, firstResult);
    }

    private List<OrdenItem> findOrdenItemEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(OrdenItem.class));
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

    public OrdenItem findOrdenItem(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(OrdenItem.class, id);
        } finally {
            em.close();
        }
    }

    public int getOrdenItemCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<OrdenItem> rt = cq.from(OrdenItem.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
