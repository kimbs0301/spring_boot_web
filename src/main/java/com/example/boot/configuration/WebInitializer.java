package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gimbyeongsu
 * 
 */
public class WebInitializer {
	// extends SpringBootServletInitializer
	private static final Logger LOGGER = LoggerFactory.getLogger(WebInitializer.class);

	public WebInitializer() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	// @Override
	// protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	// LOGGER.debug("");
	// LOGGER.debug("");
	// LOGGER.debug("");
	// return application.profiles("local", "svc").sources(LocalCommandLineRunner.class, SvcCommandLineRunner.class);
	// }

	// @Override
	// public void onStartup(ServletContext container) throws ServletException {
	// LOGGER.debug("==============");
	// LOGGER.debug("");
	// LOGGER.debug("==============");
	//
	// AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
	// context.setConfigLocations("com.example.config.tomcat");
	// // container.setInitParameter("spring.config.location", "classpath:/common.properties");
	// WebInitializer.onStartup(container, context);
	// }

	// public static void onStartup(ServletContext container, AnnotationConfigWebApplicationContext context) {
	// // context.setConfigLocations("com.example.boot.web", "com.example.boot.api");
	// context.register(WebConfig.class);
	// // container.addListener(new ContextLoaderListener(context));
	// context.setServletContext(container);
	//
	// DispatcherServlet dispatcher = new DispatcherServlet(context);
	// ServletRegistration.Dynamic servlet = container.addServlet("dispatcher", dispatcher);
	//
	// servlet.setLoadOnStartup(1);
	// servlet.addMapping("/");
	// }
}
