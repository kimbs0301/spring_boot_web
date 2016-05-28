package com.example.config.embedded;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.example.config.test.JunitConfig;

@Configuration
//@ComponentScan(excludeFilters = @Filter(value = { JunitConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
@PropertySource(value = { "classpath:common.properties" })
public class EmbeddedTomcatConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedTomcatConfig.class);
	
	@Autowired
	private Environment environment;
	
	public EmbeddedTomcatConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
	
	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// EmbeddedServletContainerAutoConfiguration
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html", "text/html;charset=UTF-8");
		mappings.add("file", "multipart/form-data");
		mappings.add("json", "application/json;charset=UTF-8");
		mappings.add("xml", "application/xml;charset=UTF-8");
		factory.setMimeMappings(mappings);
		factory.setPort(8080);
		factory.setSessionTimeout(50, TimeUnit.MINUTES);
		factory.setContextPath(environment.getRequiredProperty("context.path"));

		List<ServletContextInitializer> servletContextInitializers = new ArrayList<>();
		servletContextInitializers.add(new EmbeddedTomcatWebInitalizer());
		factory.setInitializers(servletContextInitializers);
		return factory;
	}
}
