package com.supinfo.supcommercews.dao;

import com.supinfo.supcommercews.dao.jpa.JpaCategoryDao;
import com.supinfo.supcommercews.dao.jpa.JpaProductDao;
import com.supinfo.supcommercews.util.PersistenceManager;


public class DaoFactory {

	private DaoFactory() {}
	
	public static ProductDao getProductDao() {
		return new JpaProductDao(PersistenceManager.getFactory());
	}
	
	public static CategoryDao getCategoryDao() {
		return new JpaCategoryDao(PersistenceManager.getFactory());
	}
	
}
