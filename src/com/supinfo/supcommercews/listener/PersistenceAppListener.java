package com.supinfo.supcommercews.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.supinfo.supcommercews.util.PersistenceManager;

public class PersistenceAppListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		PersistenceManager.closeFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent e) {}

}
