package com.example.boot.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author gimbyeongsu
 * 
 */
@Component
public class ApplicationContextAwareImpl implements ApplicationContextAware {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationContextAwareImpl.class);
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
		LOGGER.debug("{}", applicationContext.toString());
	}
}
