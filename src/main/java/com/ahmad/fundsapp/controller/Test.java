package com.ahmad.fundsapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @Autowired
    private  JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public String test() {
        return "OK";
    }

    @GetMapping("/dbping")
    public ResponseEntity<String> dbPing() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return ResponseEntity.ok("DB alive");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("DB down");
        }
    }
}

