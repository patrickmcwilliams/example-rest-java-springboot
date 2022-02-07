package com.crowdstreet.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	private final static String DEV = "DEV";
	private final static String PROD = "PROD";
	private final static String ACTIVE_PROFILE=DEV;
	
	public static void main(String[] args) {

		SpringApplication app = new SpringApplication(DemoApplication.class);
		if (ACTIVE_PROFILE.equals(PROD)){
			app.setAdditionalProfiles("prod");
		}
		else{

		}

		app.run(args);
	}

}
