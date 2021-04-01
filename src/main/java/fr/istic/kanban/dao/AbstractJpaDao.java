package fr.istic.kanban.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.NotFoundException;

import jpa.EntityManagerHelper;

public abstract class AbstractJpaDao<K, T extends Serializable> implements IGenericDao<K, T> {

	protected Class<T> clazz;

	protected EntityManager entityManager;

	public AbstractJpaDao(Class<T> clazzToSet) {
		this.entityManager = EntityManagerHelper.getEntityManager();
		this.clazz = clazzToSet;
	}
	public T findOne(K id) {
		return entityManager.find(clazz, id);
	}
	public List<T> findAll() {
		return entityManager.createQuery("select e from " + clazz.getName() + " as e",clazz).getResultList();
	}

	public void save(T entity) {
		if(entity==null) { 
			throw new NotFoundException("Impossible d'enregistrer un élément vide");
		}
		
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.persist(entity);
		t.commit(); 
	}

	public T update(final T entity) {
		if(entity==null) { 
			throw new NotFoundException("Impossible de modifier un élément vide");
		}
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		T res = entityManager.merge(entity);
		t.commit();
		return res;

	}

	public void delete(T entity) {
		if(entity==null) { 
			throw new NotFoundException("Impossible de supprimer un élément vide");
		}
		EntityTransaction t = this.entityManager.getTransaction();
		t.begin();
		entityManager.remove(entity);
		t.commit(); 
	}

	public void deleteById(K entityId) {
		T entity = findOne(entityId); 
		delete(entity);
	}
}