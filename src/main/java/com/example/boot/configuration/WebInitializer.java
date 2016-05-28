package com.example.boot.configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements ServletContextInitializer {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebInitializer.class);

	public WebInitializer() {
		LOGGER.debug("");
	}

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		LOGGER.debug("");
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation("com.example.boot.web,com.example.boot.api");
		context.register(WebConfig.class);
		// container.addListener(new ContextLoaderListener(context));

		context.setServletContext(container);

		DispatcherServlet dispatcher = new DispatcherServlet(context);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");

	}
}
