package com.supinfo.supcommercews.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.dao.CategoryDao;
import com.supinfo.supcommercews.entity.Category;
import com.supinfo.supcommercews.exception.UnknownItemException;


public class JpaCategoryDao implements CategoryDao {
	
	private EntityManagerFactory factory;
	
	public JpaCategoryDao(EntityManagerFactory factory) {
		this.factory = factory;
	}
	
	@Override
	public void addCategory(Category category) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		try {
			t.begin();
			em.persist(category);
			t.commit();
		}catch(PersistenceException ex) {
			System.err.println(ex.getMessage());
		}finally {
			if(t.isActive())
				t.rollback();
			em.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> getAllCategories() {
		EntityManager em = factory.createEntityManager();
		List<Category> categories = new ArrayList<Category>();
		
    	categories.addAll(em.createQuery("SELECT c FROM Category c").getResultList());
    	em.close();
    	
    	return categories;
	}

	@Override
	public Category findCategory(Key id) {
		EntityManager em = factory.createEntityManager();
		
		try {
			Category category = em.find(Category.class, id);
			if(category==null) {
				throw new UnknownItemException(id);
			}
			
			return category;
			
		} finally {
			em.close();
		}
		
	}

}
