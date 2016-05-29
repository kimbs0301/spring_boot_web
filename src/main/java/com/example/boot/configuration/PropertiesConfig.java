package com.example.boot.configuration;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class PropertiesConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);
	
	@Autowired
	private Environment environment;
	
	public PropertiesConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
	
	@PreDestroy
	public void destroy() {
		LOGGER.debug("spring.profiles.active={}", environment.getRequiredProperty("spring.profiles.active"));
	}
}
