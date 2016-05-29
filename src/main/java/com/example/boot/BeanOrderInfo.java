package com.example.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author gimbyeongsu
 * 
 */
public class BeanOrderInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanOrderInfo.class);

	public BeanOrderInfo() {

	}

	public BeanOrderInfo(String name) {
		LOGGER.debug(name);
	}
}
