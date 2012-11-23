package com.supinfo.supcommercews.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.entity.Product;


public interface ProductDao {

	public void addProduct(Product product);
	public void updateProduct(Product product);
	public void removeProduct(Key id);
	public List<Product> getAllProducts();
	public Product findProduct(Key id);

}
