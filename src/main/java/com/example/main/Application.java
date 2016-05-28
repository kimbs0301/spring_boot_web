package com.example.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.example.config.commandline.CommandLineConfig;
import com.example.config.embedded.EmbeddedTomcatConfig;

// https://github.com/syakuis/syaku-gradle/tree/spring-hibernate5
// http://credemol.blogspot.kr/2011/01/spring-junit-transaction.html
// http://blog.sbcoba.com/31
// http://docs.spring.io/spring-boot/docs/1.3.x/reference/htmlsingle/
// https://github.com/bkielczewski/example-spring-mvc-initializer
// https://breadmj.wordpress.com/2013/08/04/spring-3-only-java-config-without-xml/
// http://websystique.com/springmvc/spring-mvc-requestbody-responsebody-example/
// http://millky.com/@origoni/post/1144?language=ko_kr
// https://github.com/origoni/spring-blog
// http://www.mkyong.com/spring-mvc/spring-4-mvc-ajax-hello-world-example/
// http://blog.woniper.net/231
// java -jar myproject.jar --spring.config.name=myproject

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
//@ImportResource("classpath:spring/root-context.xml")
//@ComponentScan(excludeFilters = @Filter(value = { JunitConfig.class,
//		WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
@Import(CommandLineConfig.class)
@ComponentScan(basePackageClasses = EmbeddedTomcatConfig.class)
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
		SpringApplication springApplication = springApplicationBuilder.build();
		springApplication.run(args);
	}
}
