package com.example.boot.configuration.resolver;

import java.util.Locale;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author gimbyeongsu
 * 
 */
public class JsonViewResolver implements ViewResolver {
	private ObjectMapper objectMapper;
	
	public JsonViewResolver(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public View resolveViewName(String viewName, Locale locale) throws Exception {
		MappingJackson2JsonView view = new MappingJackson2JsonView(objectMapper);
		// view.setPrettyPrint(true);
		return view;
	}

}