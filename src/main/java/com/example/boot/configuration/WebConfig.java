package com.example.boot.configuration;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.WebMvcAutoConfiguration.EnableWebMvcConfiguration;
import org.springframework.boot.autoconfigure.web.WebMvcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.example.boot.BeanOrderInfo;
import com.example.boot.account.model.AccountXml;
import com.example.boot.configuration.resolver.JsonViewResolver;
import com.example.boot.configuration.resolver.XmlViewResolver;
import com.example.config.test.JunitConfig;
import com.example.main.Application;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

// http://docs.spring.io/spring/docs/current/spring-framework-reference/html/remoting.html#rest-message-conversion

@Configuration
@EnableWebMvc
@Import(value = {EnableWebMvcConfiguration.class})
@EnableConfigurationProperties({ WebMvcProperties.class, ResourceProperties.class })
//@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = @Filter(value = { Application.class }, type = FilterType.ASSIGNABLE_TYPE))
@ComponentScan(basePackages = { "com.example.boot" })
public class WebConfig extends WebMvcConfigurerAdapter {
	private static final Logger LOGGER = LoggerFactory.getLogger(WebConfig.class);

	public WebConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}
	
	@Bean(name="BeanOrderInfo_web")
	public BeanOrderInfo test() {
		return new BeanOrderInfo("web");
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		LOGGER.debug("");
	}

	@Override
	// @Order(1000)
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.ignoreAcceptHeader(true) //
				.defaultContentType(MediaType.TEXT_HTML) //
				.mediaType("html", MediaType.TEXT_HTML) //
				.mediaType("file", MediaType.MULTIPART_FORM_DATA) //
				.mediaType("xml", MediaType.APPLICATION_XML) //
				.mediaType("json", MediaType.APPLICATION_JSON);
	}

	@Bean
	// @Order(Ordered.LOWEST_PRECEDENCE)
	public ViewResolver contentNegotiatingViewResolver(ContentNegotiationManager manager) {
		ContentNegotiatingViewResolver resolver = new ContentNegotiatingViewResolver();
		resolver.setOrder(1);
		resolver.setContentNegotiationManager(manager);
		List<ViewResolver> resolvers = new ArrayList<ViewResolver>();
		resolvers.add(jsonViewResolver());
		resolvers.add(xmlViewResolver());
		resolvers.add(jspViewResolver());
		resolvers.add(new BeanNameViewResolver());
		resolver.setViewResolvers(resolvers);
		return resolver;
	}

	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver commonsMultipartResolver() {
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver();
		commonsMultipartResolver.setDefaultEncoding("utf-8");
		commonsMultipartResolver.setMaxUploadSize(1024);
		return commonsMultipartResolver;
	}

	@Bean
	public ViewResolver xmlViewResolver() {
		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
		marshaller.setClassesToBeBound(AccountXml.class);
		return new XmlViewResolver(marshaller);
	}

	@Bean
	public ViewResolver jsonViewResolver() {
		return new JsonViewResolver();
	}

	@Bean
	public ViewResolver jspViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(mappingJackson2HttpMessageConverter());
		converters.add(mappingJackson2XmlHttpMessageConverter());
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper()
				.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		return converter;
	}

	@Bean
	public MappingJackson2XmlHttpMessageConverter mappingJackson2XmlHttpMessageConverter() {
		MappingJackson2XmlHttpMessageConverter converter = new MappingJackson2XmlHttpMessageConverter();
		List<MediaType> supportedMediaTypes = new ArrayList<>();
		supportedMediaTypes.add(MediaType.APPLICATION_XML);
		converter.setSupportedMediaTypes(supportedMediaTypes);
		return converter;
	}
}
