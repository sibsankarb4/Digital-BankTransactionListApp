package com.transactions.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * This is a common application initializer class for Spring by overriding some
 * methods from DispatcherServletInitializer class
 * 
 * @author Sibsankar Bera
 * @version 1.0
 * @since 2018-08-31
 */
public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		return null;
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

}