package com.scaler.blog.blogapp.security.jwt;

import com.scaler.blog.blogapp.users.UserResponseDto;
import com.scaler.blog.blogapp.users.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationManager implements AuthenticationManager {
    private JWTService jwtService;
    private UsersService usersService;

    public JWTAuthenticationManager(JWTService jwtService, UsersService usersService) {
        this.jwtService = jwtService;
        this.usersService = usersService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if(authentication instanceof JWTAuthentication){
            JWTAuthentication jwtAuthentication = (JWTAuthentication) authentication;
            String jwtToken = jwtAuthentication.getCredentials();
            String userName = jwtService.getUserNameFromJwt(jwtToken);
            UserResponseDto user = usersService.getUserByUserName(userName);
            jwtAuthentication.setUser(user);
            return jwtAuthentication;
        }
        return null;
    }
}
