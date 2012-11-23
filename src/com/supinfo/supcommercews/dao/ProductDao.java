package com.supinfo.supcommercews.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.entity.Product;


public interface ProductDao {

	/**
	 * Persist a new Product
	 * @param product
	 */
	public void addProduct(Product product);
	
	/**
	 * Update the product in parameter
	 * @param product
	 */
	public void updateProduct(Product product);
	
	/**
	 * Remove a product
	 * @param id : the key of the product to remove
	 */
	public void removeProduct(Key id);
	
	/**
	 * Get all products
	 * @return a list
	 */
	public List<Product> getAllProducts();
	
	/**
	 * Retrieve a product thanks to his key
	 * @param id : the key
	 * @return the product if exists, else null
	 */
	public Product findProduct(Key id);

}
