package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;

import com.example.boot.Application;

@Configuration
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = @Filter(value = { Application.class,
		EmbeddedTomcatConfig.class, WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
@PropertySource(value = { "classpath:common.properties", "classpath:database.properties" })
public class JunitConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(JunitConfig.class);

	public JunitConfig() {
		LOGGER.debug("");
	}
}
