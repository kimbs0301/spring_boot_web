package com.example.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

import com.example.boot.configuration.JunitConfig;
import com.example.boot.configuration.WebConfig;

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

@Configuration
@ImportResource("classpath:spring/root-context.xml")
@ComponentScan(basePackages = { "com.example.boot" }, excludeFilters = @Filter(value = { JunitConfig.class,
		WebConfig.class }, type = FilterType.ASSIGNABLE_TYPE))
@PropertySource(value = { "classpath:common.properties", "classpath:database.properties" })
public class Application {
	private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

	// java -jar myproject.jar --spring.config.name=myproject
	
	@Bean
	public CommandLineRunner init() {
		// init

		return new CommandLineRunner() {

			@Override
			public void run(String... args) throws Exception {
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
	
	@Bean
	public BeanOrderInfo test() {
		return new BeanOrderInfo("app");
	}
	
//	@Bean
//	public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
//		return new PropertySourcesPlaceholderConfigurer();
//	}

	public static void main(String[] args) {
		SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Application.class);
		SpringApplication springApplication = springApplicationBuilder.build();
		springApplication.run(args);
	}
}
