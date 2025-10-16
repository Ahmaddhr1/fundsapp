package com.ahmad.fundsapp.dto;

public class AuthResponse {
    private Long id;
    private String username;
    private String token;

    public AuthResponse(Long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getToken() { return token; }
}
