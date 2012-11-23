package com.supinfo.supcommercews.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.entity.Category;


public interface CategoryDao {

	/**
	 * Persist a new category
	 * @param category
	 */
	public void addCategory(Category category);
	
	/**
	 * Get all categories
	 * @return a list
	 */
	public List<Category> getAllCategories();
	
	/**
	 * Retrieve a category thanks to her key
	 * @param id : the key
	 * @return a Category if exists or null
	 */
	public Category findCategory(Key id);
}
