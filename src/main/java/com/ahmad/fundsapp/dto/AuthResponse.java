package com.ahmad.fundsapp.dto;

public class AuthResponse {
    private Long id;
    private String username;
    private String token;
    private String error;

    public AuthResponse(Long id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
        this.error = null;
    }

    public AuthResponse(String error) {
        this.error = error;
        this.id = null;
        this.username = null;
        this.token = null;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getToken() { return token; }
    public String getError() { return error; }
}