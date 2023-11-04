
package com.softtek.m4.repositorio;

import com.softtek.m4.exceptions.NonexistentEntityException;
import com.softtek.m4.modelo.entidades.Tarea;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;

public class TareaJpaController implements Serializable, TareaDao {

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public TareaJpaController(){
        // Creación del EMF a partir de persistence-unit (persistence.xml)
        this.emf = Persistence.createEntityManagerFactory("TareaPU");
    }
    
    @Override
    public void create(Tarea tarea) {
        EntityManager em = null;
        try {
            // Obtener instancia del EntityManager
            em = getEntityManager();
            
            // Conexión a la base de datos
            em.getTransaction().begin();
            
            // Dar de alta a la tarea
            em.persist(tarea);
            
            // Commitear los cambios
            em.getTransaction().commit();
        } finally {
            
            // Cerrar la conexión a la base de datos
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void edit(Tarea tarea) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            // Obtener instancia del EntityManager
            em = getEntityManager();
            
            // Conexión a la base de datos
            em.getTransaction().begin();
            
            // Modificar la entidad antigua con la nueva
            tarea = em.merge(tarea);
            
            // Commitear los cambios
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                
                // Si ocurrió un error sin mensaje, se verifica que exista la tarea
                Integer id = tarea.getId();
                if (findTarea(id) == null) {
                    throw new NonexistentEntityException(
                            "No existe una tarea con el ID: " + id);
                }
            }
            throw ex;
        } finally {
            
            // Cerrar la conexión a la base de datos
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            // Obtener instancia del EntityManager
            em = getEntityManager();
            
            // Conexión a la base de datos
            em.getTransaction().begin();
            
            // Se instancia la tarea
            Tarea tarea;
            
            // Si se logra obtener su ID, significa que la tarea existe
            try {
                tarea = em.getReference(Tarea.class, id);
                tarea.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException(
                        "No existe una tarea con el ID: " + id);
            }
            
            // Si no se lanzó ningun error, se puede eliminar la tarea
            em.remove(tarea);
            
            // Se commitean los cambios
            em.getTransaction().commit();
        } finally {
            
            // Cerrar la conexión a la base de datos
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Tarea> findTareaEntities() {
        return findTareaEntities(true, -1, -1);
    }

    private List<Tarea> findTareaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tarea.class));
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

    @Override
    public Tarea findTarea(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Tarea.class, id);
        } finally {
            em.close();
        }
    }   
}
