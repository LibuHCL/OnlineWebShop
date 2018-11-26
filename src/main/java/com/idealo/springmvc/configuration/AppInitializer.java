package com.idealo.springmvc.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import javax.servlet.Filter;

import com.idealo.springmvc.filter.CORSFilter;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	private static final Class<?>[] NO_FILES = {};

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { AppConfig.class };
	}
 
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return NO_FILES;
	}
 
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[]{ new CORSFilter()};
	}

}
