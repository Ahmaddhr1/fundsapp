package com.ahmad.fundsapp.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class EnvConfig {

    private final Dotenv dotenv;

    public EnvConfig() {
        Dotenv temp = null;
        try {
            temp = Dotenv.load();
        } catch (Exception e) {
        }
        this.dotenv = temp;
    }

    public String get(String key) {
        if (dotenv != null && dotenv.get(key) != null) {
            return dotenv.get(key);
        }
        String envValue = System.getenv(key);
        if (envValue == null) {
            throw new RuntimeException("Environment variable " + key + " not set!");
        }
        return envValue;
    }
}
