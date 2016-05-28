package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.example.boot.BeanOrderInfo;

@Configuration
@ImportResource("classpath:spring/root-context.xml")
@PropertySource(value = { "classpath:common.properties", "classpath:database.properties" })
public class RootConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(RootConfig.class);
	
	public RootConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
	
	@Bean(name="BeanOrderInfo_app")
	public BeanOrderInfo test() {
		return new BeanOrderInfo("app");
	}
	
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}
}
