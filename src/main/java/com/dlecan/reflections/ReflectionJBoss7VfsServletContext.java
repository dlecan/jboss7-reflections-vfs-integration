package com.dlecan.reflections;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReflectionJBoss7VfsServletContext implements
		ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		ReflectionJBoss7Vfs.initVfs();

	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing
	}

}
