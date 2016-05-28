package com.example.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanOrderInfo {
	private static final Logger LOGGER = LoggerFactory.getLogger(BeanOrderInfo.class);

	private String name;

	public BeanOrderInfo() {

	}

	public BeanOrderInfo(String name) {
		this.name = name;
		LOGGER.debug(this.name);
	}
}
