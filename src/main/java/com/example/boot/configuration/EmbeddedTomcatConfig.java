package com.example.boot.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class EmbeddedTomcatConfig {

	@Autowired
	private Environment environment;
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// EmbeddedServletContainerAutoConfiguration
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		// MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		MimeMappings mappings = new MimeMappings();
		mappings.add("css", "text/css");
		mappings.add("txt", "text/plain");
		mappings.add("png", "image/png");
		mappings.add("jpeg", "image/jpeg");
		mappings.add("jpg", "image/jpeg");
		mappings.add("zip", "application/zip");
		mappings.add("bin", "application/octet-stream");
		mappings.add("html", "text/html;charset=UTF-8");
		mappings.add("file", "multipart/form-data");
		mappings.add("json", "application/json;charset=UTF-8");
		mappings.add("xml", "application/xml;charset=UTF-8");
		factory.setMimeMappings(mappings);
		factory.setPort(8080);
		factory.setSessionTimeout(50, TimeUnit.MINUTES);
		factory.setContextPath(environment.getRequiredProperty("context.path"));

		List<ServletContextInitializer> servletContextInitializers = new ArrayList<>();
		servletContextInitializers.add(new WebInitializer());
		factory.setInitializers(servletContextInitializers);
		return factory;
	}
}
