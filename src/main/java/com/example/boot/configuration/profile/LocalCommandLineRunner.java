package com.example.boot.configuration.profile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile(value = "local")
public class LocalCommandLineRunner implements CommandLineRunner {
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalCommandLineRunner.class);
	
	@Override
	public void run(String... args) throws Exception {
		LOGGER.debug("");
		for(String each : args) {
			LOGGER.debug(each);
		}
	}
}
