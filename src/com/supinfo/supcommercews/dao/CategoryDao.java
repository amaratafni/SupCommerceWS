package com.supinfo.supcommercews.dao;

import java.util.List;

import com.google.appengine.api.datastore.Key;
import com.supinfo.supcommercews.entity.Category;


public interface CategoryDao {

	public void addCategory(Category category);
	public List<Category> getAllCategories();
	public Category findCategory(Key id);
}
