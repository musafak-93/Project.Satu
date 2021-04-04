/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Praktikum.project.satu.access;

import Praktikum.project.satu.access.exceptions.NonexistentEntityException;
import Praktikum.project.satu.access.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Musafak
 */
public class MydataJpaController implements Serializable {

    public MydataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("Praktikum_project.satu_jar_0.0.1-SNAPSHOTPU");

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public MydataJpaController() {
    }
    

    public void create(Mydata mydata) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mydata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMydata(mydata.getId()) != null) {
                throw new PreexistingEntityException("Mydata " + mydata + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mydata mydata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mydata = em.merge(mydata);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = mydata.getId();
                if (findMydata(id) == null) {
                    throw new NonexistentEntityException("The mydata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mydata mydata;
            try {
                mydata = em.getReference(Mydata.class, id);
                mydata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mydata with id " + id + " no longer exists.", enfe);
            }
            em.remove(mydata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mydata> findMydataEntities() {
        return findMydataEntities(true, -1, -1);
    }

    public List<Mydata> findMydataEntities(int maxResults, int firstResult) {
        return findMydataEntities(false, maxResults, firstResult);
    }

    private List<Mydata> findMydataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mydata.class));
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

    public Mydata findMydata(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mydata.class, id);
        } finally {
            em.close();
        }
    }

    public int getMydataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mydata> rt = cq.from(Mydata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
