package com.example.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {

	@Bean
	RestTemplate initRest() {
		return new RestTemplate();
	}
	
	
	
}
