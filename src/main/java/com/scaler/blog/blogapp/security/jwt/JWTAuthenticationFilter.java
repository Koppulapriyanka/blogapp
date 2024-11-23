package com.scaler.blog.blogapp.security.jwt;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.stereotype.Component;

@Component
public class JWTAuthenticationFilter extends AuthenticationFilter {

    public JWTAuthenticationFilter(JWTAuthenticationManager jwtAuthenticationManager, JWTAutheticationConverter jwtAutheticationConverter){
        super(jwtAuthenticationManager, jwtAutheticationConverter);

        this.setSuccessHandler((request, response, authentication) -> {
            SecurityContextHolder.getContext().setAuthentication(authentication);
        });
    }
}
