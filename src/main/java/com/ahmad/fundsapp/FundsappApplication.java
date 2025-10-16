package com.ahmad.fundsapp;

import io.github.cdimascio.dotenv.DotEnvException;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FundsappApplication {

	public static void main(String[] args) {

        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            throw e;
        }

        System.setProperty("spring.datasource.url",
                (dotenv != null) ? dotenv.get("DB_URL") : System.getenv("DB_URL"));
        System.setProperty("spring.datasource.username",
                (dotenv != null) ? dotenv.get("DB_USERNAME") : System.getenv("DB_USERNAME"));
        System.setProperty("spring.datasource.password",
                (dotenv != null) ? dotenv.get("DB_PASSWORD") : System.getenv("DB_PASSWORD"));
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

        System.setProperty("spring.jpa.hibernate.ddl-auto", "update");
        System.setProperty("spring.jpa.show-sql", "true");

        SpringApplication.run(FundsappApplication.class, args);
	}

}
