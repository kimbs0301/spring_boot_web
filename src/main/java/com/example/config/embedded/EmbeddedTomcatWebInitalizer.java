package com.example.config.embedded;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.example.boot.configuration.WebInitializer;

public class EmbeddedTomcatWebInitalizer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		WebInitializer.onStartup(container, context);
	}
}
