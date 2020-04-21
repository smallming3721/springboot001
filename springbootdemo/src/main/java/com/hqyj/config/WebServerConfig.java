package com.hqyj.config;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureAfter({WebMvcAutoConfiguration.class})
public class WebServerConfig {

	@Value("${myhttp.port}")
	private int port;
	@Bean
	public Connector connector(){
		Connector connector = new Connector();
		System.out.println("123332211");
		connector.setScheme("http");
		connector.setPort(port);
		return connector;
	}
	@Bean
	public ServletWebServerFactory servletWebServerFactory(){
		TomcatServletWebServerFactory tomcatFactory = new TomcatServletWebServerFactory();
		tomcatFactory.addAdditionalTomcatConnectors(connector());
		return tomcatFactory;
	}

}
