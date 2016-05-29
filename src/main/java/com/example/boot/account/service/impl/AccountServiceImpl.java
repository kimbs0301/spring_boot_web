package com.example.boot.account.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.boot.account.service.AccountService;
import com.example.boot.configuration.Config;

/**
 * @author gimbyeongsu
 * 
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private Config config;
	
	@Override
	public void config() {
		for(String each : config.getServers()) {
			LOGGER.debug("{}", each);
		}
	}
}
