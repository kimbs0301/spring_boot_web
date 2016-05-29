package com.example.config.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
@PropertySource(value = { "classpath:application-svc.properties" })
@Profile(value = { "svc" })
public class TomcatConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(TomcatConfig.class);

	public TomcatConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
}
