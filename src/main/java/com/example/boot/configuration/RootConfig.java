package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import com.example.boot.BeanOrderInfo;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
@ImportResource("classpath:spring/root-context.xml")
public class RootConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(RootConfig.class);

	public RootConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	@Bean(name = "BeanOrderInfo_app")
	public BeanOrderInfo test() {
		return new BeanOrderInfo("app");
	}

	@Bean
	public ObjectMapper objectMapper() {
		ObjectMapper om = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return om;
	}

	// @Bean
	// public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
	// return new PropertySourcesPlaceholderConfigurer();
	// }
}
