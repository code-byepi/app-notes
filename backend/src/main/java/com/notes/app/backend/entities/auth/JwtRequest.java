package com.notes.app.backend.entities.auth;

public class JwtRequest {
    private String email;
    private String password;

    public JwtRequest() {
    }

    public JwtRequest(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
