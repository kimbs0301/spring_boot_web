package com.example.boot.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

// http://docs.spring.io/spring-boot/docs/1.3.5.RELEASE/reference/html/boot-features-external-config.html

@Component
@ConfigurationProperties(prefix = "my")
public class Config {
	private List<String> servers = new ArrayList<String>();

	public List<String> getServers() {
		return this.servers;
	}
}
