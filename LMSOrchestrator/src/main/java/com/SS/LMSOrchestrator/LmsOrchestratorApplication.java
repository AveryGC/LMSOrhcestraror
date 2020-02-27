package com.SS.LMSOrchestrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


@SpringBootApplication
@EnableEurekaClient
public class LmsOrchestratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(LmsOrchestratorApplication.class, args);
	}
	

}
