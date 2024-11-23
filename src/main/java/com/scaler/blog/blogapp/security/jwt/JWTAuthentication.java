package com.scaler.blog.blogapp.security.jwt;

import com.scaler.blog.blogapp.users.UserResponseDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.security.auth.Subject;
import java.util.Collection;

public class JWTAuthentication implements Authentication {

    private String jwtToken;
    private UserResponseDto user;

    public JWTAuthentication(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    void setUser(UserResponseDto user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getCredentials() {
        return jwtToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public UserResponseDto getPrincipal() {
        return user;
    }

    @Override
    public boolean isAuthenticated() {
        return user != null ;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean implies(Subject subject) {
        return Authentication.super.implies(subject);
    }
}
