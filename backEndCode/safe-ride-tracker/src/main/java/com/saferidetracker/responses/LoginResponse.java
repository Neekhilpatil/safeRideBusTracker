package com.saferidetracker.responses;

public class LoginResponse {
    private String token;
    private Long expiresIn;

    // Fluent setter for token
    public LoginResponse setToken(String token) {
        this.token = token;
        return this;  // Return the current object for method chaining
    }

    // Fluent setter for expiresIn
    public LoginResponse setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
        return this;  // Return the current object for method chaining
    }

    // Getters
    public String getToken() {
        return token;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }
}
