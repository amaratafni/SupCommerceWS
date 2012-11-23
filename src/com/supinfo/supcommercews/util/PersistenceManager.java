package com.supinfo.supcommercews.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager {

	private static EntityManagerFactory factory;
	
	public static EntityManagerFactory getFactory() {
		if(factory==null)
			factory = Persistence.createEntityManagerFactory("supcommercews146829");
		return factory;
	}
	
	public static void closeFactory() {
		if(factory!=null && factory.isOpen())
			factory.close();
	}
}
