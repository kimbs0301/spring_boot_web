package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.example.boot.Application;

@Configuration
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = @Filter(value = {
		Application.class, WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
public class JunitConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(JunitConfig.class);
	
	public JunitConfig() {
		LOGGER.error("");
	}
}
