package com.example.config.commandline;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class CommandLineConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommandLineConfig.class);
	
	@Autowired
	private Environment environment;
	
	@Bean
	public CommandLineRunner init() {
		// init

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
				LOGGER.debug("{}", environment.getRequiredProperty("spring.main.banner-mode"));
				LOGGER.debug("CommandLineSize:{}", args.length);
				for (String command : args) {
					LOGGER.debug("{}", command);
				}
			}
		};

		// return (String[] args) -> {
		// System.out.println("ok");
		// };
	}
}
