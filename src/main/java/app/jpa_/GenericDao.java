package app.jpa_;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;

import static app.jpa_.JPAUtil.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import app.jpa_.exceptions.EntityNotFoundException;

/**
 * A GenericDao is an abstract class that provides basic CRUD operations for a specified entity type.
 * This class uses JPA (Java Persistence API) to interact with the database.
 *
 * @version 1.0
 * @param <T> the type of the entity to be managed by this DAO.
 * @author Eliezer Brasilian
 * 
 * Visit repository link on GitHub: <a href="https://github.com/eliezerBrasilian/jsf-template">GitHub</a>
 */
public abstract class GenericDao<T> {
	protected Class<T> entityClass;
	
	/**
     * Constructs a GenericDao for the specified entity class.
     *
     * @param entityClass the class of the entity to be managed.
     */
	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}
	
	 /**
     * Saves a new entity to the database.
     *
     * @param entity the entity to be saved.
     */
	
	//OK
	public void save(T entity) {
		EntityManager entityManager = (EntityManager) getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		entityManager.persist(entity);
		
		entityTransaction.commit();
		System.out.println("row added");
		entityManager.close();
	}

	/**
     * Deletes an entity from the database by its ID.
     *
     * @param id the ID of the entity to be deleted.
     * @throws EntityNotFoundException if the entity is not found.
     */
	
	//OK
	public void deleteById(Object id) throws EntityNotFoundException {
		EntityManager entityManager = (EntityManager) getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		T entity = entityManager.find(entityClass, id);
		
		if(entity == null) throw new EntityNotFoundException();
		
		entityManager.remove(entity);
		System.out.println("row was removed");
		entityTransaction.commit();
		entityManager.close();
	}
	
	/**
     * Deletes all entities of the specified type from the database.
     */
	public void deleteAll() {
	    EntityManager entityManager = getEntityManager();
	    EntityTransaction entityTransaction =  entityManager.getTransaction();
	    entityTransaction.begin();
	    
	    String queryString = "DELETE FROM " + entityClass.getSimpleName();
	    System.out.println("classe name: " + entityClass.getSimpleName());
	    
	    try {
	    	 var query = entityManager.createQuery(queryString);
	 	     int deletedCount = query.executeUpdate();
	 	     System.out.println(deletedCount + " rows were removed.");
	 	    
	 	     entityTransaction.commit();
	 	    
	    }catch(Exception e) {
	    	System.out.println(e.getMessage());
	    }finally {
	    	entityManager.close();
	    }
	}

	 /**
     * Updates an existing entity in the database.
     *
     * @param entity the entity to be updated.
     * @return the updated entity.
     * @throws EntityNotFoundException if the entity is null.
     */
	
	//OK
	public T update(T entity) {
		EntityManager entityManager = (EntityManager) getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		if(entity == null) throw new EntityNotFoundException();
		
		entityManager.merge(entity);
		System.out.println("entity was updated");
		
		entityTransaction.commit();
		entityManager.close();
		return entity;
	}

	/**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity to be found.
     * @return the found entity or null if not found.
     */
	
	//OK
	public T findById(Object id) {
		EntityManager entityManager = (EntityManager) getEntityManager();
		
		T entity = entityManager.find(entityClass, id);
		System.out.println("entity was found");
		
		entityManager.close();
		return entity;
	}
	
	/**
     * Retrieves all entities of the specified type from the database.
     *
     * @return a list of all entities.
     */
	
	//OK
	public List<T> findAll() {
		EntityManager entityManager = getEntityManager();
		
		TypedQuery<T> query = entityManager.
				createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e",entityClass);
		
		List<T> resultList = query.getResultList();
		
		entityManager.close();
		
		return resultList;
	}
	
	 /**
     * Finds entities by a specified field and orders them by another field.
     *
     * @param fieldName the name of the field to filter by.
     * @param value the value of the field to filter by.
     * @param orderByField the field to order the results by.
     * @param order the order direction (ascending or descending).
     * @return a list of filtered and ordered entities.
     */
	public List<T> findByFieldAndOrderBy(String fieldName, 
			Object value, String orderByField, Order order) {
	    EntityManager entityManager = (EntityManager) getEntityManager();
	    try {
	        
	    	String queryString = String.format(
	    		    "SELECT e FROM %s e WHERE e.%s = :value ORDER BY e.%s %s",
	    		    entityClass.getSimpleName(),
	    		    fieldName,
	    		    orderByField,
	    		    order.value
	    		);

	        return entityManager.createQuery(queryString, entityClass)
	                            .setParameter("value", value)
	                            .getResultList();
	    } finally {
	        entityManager.close();
	    }
	}
	
	/**
     * Executes a custom JPQL query with parameters.
     *
     * @param jpql the JPQL query string.
     * @param parameters a map of parameters to set in the query.
     * @return a list of results returned by the query.
     */
	@SuppressWarnings("unchecked")
	public List<T> executeQuery(String jpql, Map<String, Object> parameters) {
	    EntityManager entityManager = (EntityManager) getEntityManager();
	    EntityTransaction entityTransaction = entityManager.getTransaction();
	    entityTransaction.begin();

	    List<T> results = new ArrayList<T>();
	    try {
	    	TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
	        
	        if (parameters != null) {
	            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
	                query.setParameter(entry.getKey(), entry.getValue());
	            }
	        }

	        results = query.getResultList();
	        entityTransaction.commit();
	    } catch (Exception e) {
	        if (entityTransaction.isActive()) {
	            entityTransaction.rollback(); // Reverte a transação em caso de erro
	        }
	        System.out.println("Error executing query: " + e.getMessage());
	    } finally {
	        entityManager.close();
	    }
	    
	    return results;
	}

}
