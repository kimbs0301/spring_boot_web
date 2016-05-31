package com.example.config.embedded;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.catalina.connector.Connector;
import org.apache.catalina.valves.AccessLogValve;
import org.apache.coyote.http11.Http11NioProtocol;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;

// http://docs.spring.io/spring-boot/docs/1.3.5.RELEASE/reference/html/howto-embedded-servlet-containers.html
// https://github.com/jthoms/spring-boot-embedded-tomcat-ssl

/**
 * @author gimbyeongsu
 * 
 */
@Configuration
public class EmbeddedTomcatConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(EmbeddedTomcatConfig.class);

	@Autowired
	private Environment environment;

	public EmbeddedTomcatConfig() {
		LOGGER.debug("==============");
		LOGGER.debug("");
		LOGGER.debug("==============");
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		// EmbeddedServletContainerAutoConfiguration
		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory(
				environment.getRequiredProperty("context.path"), 8080);
		factory.setProtocol("org.apache.coyote.http11.Http11NioProtocol");
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html", "text/html;charset=UTF-8");
		mappings.add("file", "multipart/form-data");
		mappings.add("json", "application/json;charset=UTF-8");
		mappings.add("xml", "application/xml;charset=UTF-8");
		factory.setMimeMappings(mappings);
		factory.setSessionTimeout(50, TimeUnit.MINUTES);
		factory.setUriEncoding(Charset.forName("UTF-8"));

		AccessLogValve accessLogValve = new AccessLogValve();
		accessLogValve.setDirectory("/temp");
		// '%h %l %u %t "%r" %s %b %D'
		accessLogValve.setPattern("%{yyyy-MM-dd HH:mm:ss}t\t%a\t%r\t%{Referer}i\t%{User-Agent}i\t%D\t%I");
		accessLogValve.setSuffix(".log");
		factory.addContextValves(accessLogValve);

		// Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		// connector.setPort(8081);
		// factory.addAdditionalTomcatConnectors(connector);

		List<ServletContextInitializer> servletContextInitializers = new ArrayList<>();
		servletContextInitializers.add(new EmbeddedTomcatWebInitalizer());
		factory.setInitializers(servletContextInitializers);

		factory.addAdditionalTomcatConnectors(createSslConnector());
		return factory;
	}

	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
			File keystore = new ClassPathResource("tomcat.jks").getFile();
			File truststore = new ClassPathResource("truststore.jks").getFile();
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(8443);
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(keystore.getAbsolutePath());
			protocol.setKeystorePass("123456");
			protocol.setTruststoreFile(truststore.getAbsolutePath());
			protocol.setTruststorePass("123456");
			// protocol.setClientAuth("true"); // 웹브라우져 client.p12 인증서 등록 필요
			protocol.setClientAuth("false");
			protocol.setKeyAlias("tomcat_server");
			protocol.setSslProtocol("TLS");
			return connector;
		} catch (IOException ex) {
			throw new IllegalStateException("can't access keystore: [" + "keystore" + "] or truststore: [" + "keystore"
					+ "]", ex);
		}
	}
}
