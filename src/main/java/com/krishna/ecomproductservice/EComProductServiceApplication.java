package com.krishna.ecomproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EComProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EComProductServiceApplication.class, args);
	}

}
