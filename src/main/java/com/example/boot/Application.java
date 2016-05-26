package com.example.boot;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import com.example.boot.configuration.JunitConfig;
import com.example.boot.configuration.WebConfig;
import com.example.boot.configuration.WebInitializer;

// https://github.com/syakuis/syaku-gradle/tree/spring-hibernate5
// http://credemol.blogspot.kr/2011/01/spring-junit-transaction.html
// http://blog.sbcoba.com/31
// http://docs.spring.io/spring-boot/docs/1.3.x/reference/htmlsingle/
// https://github.com/bkielczewski/example-spring-mvc-initializer
// https://breadmj.wordpress.com/2013/08/04/spring-3-only-java-config-without-xml/
// http://websystique.com/springmvc/spring-mvc-requestbody-responsebody-example/
// http://millky.com/@origoni/post/1144?language=ko_kr
// https://github.com/origoni/spring-blog

@Configuration
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = @Filter(value = {
		JunitConfig.class, WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
public class Application //extends SpringBootServletInitializer
{
	
//	@Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		System.out.println();
//		System.out.println();
//		System.out.println();
//        return application.parent(Global.class).sources(Application.class).profiles("container");
//    }

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// org.springframework.boot.autoconfigure.web.EmbeddedServletContainerAutoConfiguration;
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html", "text/html;charset=UTF-8");
		mappings.add("json", "application/json;charset=UTF-8");
		mappings.add("xml", "application/xml;charset=UTF-8");
		factory.setMimeMappings(mappings);
		factory.setPort(8080);
		factory.setSessionTimeout(50, TimeUnit.MINUTES);
		factory.setContextPath("/mvc");
		
		factory.setDocumentRoot(new File("/workspace/luna/spring_boot_example/src/main/webapp"));
		factory.setBaseDirectory(new File("/workspace/luna/spring_boot_example/src/main/webapp"));

		
		List<ServletContextInitializer> servletContextInitializers = new ArrayList<>();
		servletContextInitializers.add(new WebInitializer());
		factory.setInitializers(servletContextInitializers);
		return factory;
	}

	public static void main(String[] args) {
//		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
//		SpringApplication springApplication = springApplicationBuilder.build();
//		springApplication.run(args);
		
		SpringApplication.run(Application.class, args);
	}
}
