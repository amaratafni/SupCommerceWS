package com.supinfo.supcommercews.dao.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.dao.DaoFactory;
import com.supinfo.supcommercews.dao.ProductDao;
import com.supinfo.supcommercews.entity.Category;
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
    		em.remove(em.find(Product.class, id));
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
//			Product productMerged = em.merge(product);
			Query query = em.createQuery("UPDATE Product p SET p.name=:name, p.content=:content, p.price=:price, p.category=:category WHERE p.id=:key");
			query.setParameter("name", product.getName());
			query.setParameter("content", product.getContent());
			query.setParameter("price", product.getPrice());
			query.setParameter("category", product.getCategory());
			query.setParameter("key", product.getId());
			
			query.executeUpdate();
//			if(productMerged!=null)
//				em.refresh(productMerged);
//			else System.err.println("Object couldn't be merge.");
			t.commit();
		} catch(PersistenceException ex) {
			System.err.println(ex.getMessage());
		} finally {
			if(t!=null && t.isActive()) t.rollback();
			em.close();
		}
		
	}
}
