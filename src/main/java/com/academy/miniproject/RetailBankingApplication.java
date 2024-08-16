package com.academy.miniproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatReactiveWebServerFactory;
import org.springframework.boot.web.embedded.tomcat.TomcatWebServer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class RetailBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RetailBankingApplication.class, args);
		System.out.println("Hello tested");
		
	}


	//default server
	@Bean
	public ConfigurableServletWebServerFactory webServerFactory() 
	{
	    JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
	    factory.setPort(8081);
	    
	   /* TomcatReactiveWebServerFactory factory2 = new TomcatReactiveWebServerFactory();
	    factory2.setPort(8082);*/
	    return factory;
	}
	
	
	
	
}
