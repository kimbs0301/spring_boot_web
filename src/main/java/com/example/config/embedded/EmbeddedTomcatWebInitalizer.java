package com.example.config.embedded;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import com.example.boot.configuration.SecurityFilter;
import com.example.boot.configuration.TrackingFilter;
import com.example.boot.configuration.WebConfig;

/**
 * @author gimbyeongsu
 * 
 */
public class EmbeddedTomcatWebInitalizer implements ServletContextInitializer {

	@Override
	public void onStartup(ServletContext container) throws ServletException {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		// WebInitializer.onStartup(container, context);

		// context.setConfigLocations("com.example.boot.web", "com.example.boot.api");
		context.register(WebConfig.class);
		// container.addListener(new ContextLoaderListener(context));
		context.setServletContext(container);

		DispatcherServlet dispatcher = new DispatcherServlet(context);
		ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);

		FilterRegistration.Dynamic characterEncodingFilter = container.addFilter("characterEncodingFilter",
				new CharacterEncodingFilter("UTF-8", true));
		characterEncodingFilter.addMappingForUrlPatterns(null, true, "/*");
		container.addFilter("securityFilter", new SecurityFilter()).addMappingForUrlPatterns(null, false, "/*");
		container.addFilter("trackingFilter", new TrackingFilter()).addMappingForUrlPatterns(null, false, "/*");

		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}
