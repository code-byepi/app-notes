package com.notes.app.backend.entities.auth;

import org.springframework.security.core.GrantedAuthority;

public class Auth implements GrantedAuthority {

    private static final long serialVersionUID = 1L;
    private String authority;

    public Auth(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

}
