package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
public class CacheConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfig.class);

	public CacheConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	@Lazy
	@Bean(name = "cacheName")
	public String cacheName() {
		LOGGER.debug("==============");
		return "cache";
	}
}
