package com.supinfo.supcommercews.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.dao.ProductDao;
import com.supinfo.supcommercews.entity.Product;


public class JpaProductDao implements ProductDao{

	private EntityManagerFactory factory;
	
	public JpaProductDao(EntityManagerFactory factory) {
		this.factory = factory;
	}

	@Override
	public void addProduct(Product product) {
		EntityManager em = factory.createEntityManager();
    	EntityTransaction t = em.getTransaction();
    	
    	try {
    		t.begin();
			em.persist(product);
    		t.commit();
    	}catch(PersistenceException ex){
    		System.err.println(ex.getMessage());
    	}finally{
    		if(t.isActive())
    			t.rollback();
    		em.close();
    	}
	}

	@Override
	public void removeProduct(Key id) {
		EntityManager em = factory.createEntityManager();
    	EntityTransaction t = em.getTransaction();
    	
    	try {
    		t.begin();
    		Product product = em.find(Product.class, id);
    		em.refresh(product);
    		em.remove(product);
    		t.commit();
    	}catch(PersistenceException ex){
    		System.err.println(ex.getMessage());
    	}finally{
    		if(t.isActive())
    			t.rollback();
    		em.close();
    	}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProducts() {
		EntityManager em = factory.createEntityManager();
		List<Product> list = new ArrayList<Product>();
		
    	list.addAll(em.createQuery("SELECT p FROM Product p").getResultList());
    	em.close();
    	
    	return list;
	}

	@Override
	public Product findProduct(Key id) {
		EntityManager em = factory.createEntityManager();
		
		Product p = em.find(Product.class, id);
		em.close();
		
		return p;
	}

	@Override
	public void updateProduct(Product product) {
		EntityManager em = factory.createEntityManager();
		EntityTransaction t = em.getTransaction();
		
		try {
			
			t.begin();
//			em.refresh(product);
			product = em.merge(product);
			t.commit();
			
		} catch(PersistenceException ex) {
			System.err.println(ex.getMessage());
		} finally {
			if(t!=null && t.isActive()) t.rollback();
			em.close();
		}
		
	}
}
