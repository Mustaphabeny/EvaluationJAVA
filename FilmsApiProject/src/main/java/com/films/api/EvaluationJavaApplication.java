package com.films.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
public class EvaluationJavaApplication {

	public static void main(String[] args) {
		System.out.println("Started_app");
		SpringApplication.run(EvaluationJavaApplication.class, args);
		
	}
}
