package com.ahmad.fundsapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundsappApplication {

	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.load();
        System.setProperty("spring.datasource.url", dotenv.get("DB_URL"));
        System.setProperty("spring.datasource.username", dotenv.get("DB_USERNAME"));
        System.setProperty("spring.datasource.password", dotenv.get("DB_PASSWORD"));
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

        System.setProperty("spring.jpa.hibernate.ddl-auto", "validate"); // or "update"
        System.setProperty("spring.jpa.show-sql", "true");

        SpringApplication.run(FundsappApplication.class, args);
	}

}
