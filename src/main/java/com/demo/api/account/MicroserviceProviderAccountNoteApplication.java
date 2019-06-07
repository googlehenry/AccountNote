package com.demo.api.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

//@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceProviderAccountNoteApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		Logger log= LoggerFactory.getLogger(MicroserviceProviderAccountNoteApplication.class);
		log.info("starting Account Service Provider 8401...");
		SpringApplication.run(MicroserviceProviderAccountNoteApplication.class, args);
		
	}
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	        return builder.sources(MicroserviceProviderAccountNoteApplication.class);
	    }

}

