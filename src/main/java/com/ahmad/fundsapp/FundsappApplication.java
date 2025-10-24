package com.ahmad.fundsapp;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class FundsappApplication {

    public static void main(String[] args) {

        Dotenv dotenv = null;
        try {
            dotenv = Dotenv.load();
        } catch (Exception e) {
            throw new RuntimeException("Error loading  dotenv", e);
        }

        String dbUrl = (dotenv != null) ? dotenv.get("DB_URL") : System.getenv("DB_URL");
        String dbUser = (dotenv != null) ? dotenv.get("DB_USERNAME") : System.getenv("DB_USERNAME");
        String dbPass = (dotenv != null) ? dotenv.get("DB_PASSWORD") : System.getenv("DB_PASSWORD");

        if (dbUrl == null || dbUser == null || dbPass == null) {
            throw new RuntimeException("Database environment variables not set!");
        }

        System.setProperty("spring.datasource.url", dbUrl);
        System.setProperty("spring.datasource.username", dbUser);
        System.setProperty("spring.datasource.password", dbPass);
        System.setProperty("spring.datasource.driver-class-name", "org.postgresql.Driver");

        SpringApplication.run(FundsappApplication.class, args);
    }

}
