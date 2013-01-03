package com.dlecan.reflections;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ReflectionJBoss7VfsServletContext implements
		ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {

        sce.getServletContext().log("JBoss VFS initialisation for Play applications...");

		ReflectionJBoss7Vfs.initVfs();

        sce.getServletContext().log("JBoss VFS initialisation for Play applications done.");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		// Nothing
	}

}
