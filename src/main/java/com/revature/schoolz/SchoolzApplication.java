package com.revature.schoolz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SchoolzApplication {
	public static void main(String[] args) {
		// Load .env file
		Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

		// Set specific environment variables
		System.setProperty("SPRING_DATASOURCE_URL",
				dotenv.get("SPRING_DATASOURCE_URL"));
		System.setProperty("SPRING_DATASOURCE_USERNAME",
				dotenv.get("SPRING_DATASOURCE_USERNAME"));
		System.setProperty("SPRING_DATASOURCE_PASSWORD",
				dotenv.get("SPRING_DATASOURCE_PASSWORD"));
		SpringApplication.run(SchoolzApplication.class, args);
	}

}
