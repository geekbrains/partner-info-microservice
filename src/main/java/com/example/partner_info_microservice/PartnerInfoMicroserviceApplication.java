package com.example.partner_info_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class PartnerInfoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartnerInfoMicroserviceApplication.class, args);
	}


	@Bean
	public RestTemplate createRestTemplate(){
		return new RestTemplate();
	}

}
