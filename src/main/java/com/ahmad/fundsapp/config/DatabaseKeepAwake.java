package com.ahmad.fundsapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class DatabaseKeepAwake {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Scheduled(fixedRate = 300000)
    public void pingDatabase() {
        try {
            jdbcTemplate.execute("SELECT 1");
            System.out.println("✅ Database ping successful at " + java.time.LocalTime.now());
        } catch (Exception e) {
            System.err.println("❌ Database ping failed:");
        }
    }
}
