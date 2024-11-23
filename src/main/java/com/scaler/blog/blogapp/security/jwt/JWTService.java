package com.scaler.blog.blogapp.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JWTService {
    public static final String SECRET = "jwtsecrettoken";
    Algorithm algorithm = Algorithm.HMAC256(SECRET);

    public String createJwt(String userName) {
        if(userName == null) {
            throw new IllegalArgumentException("cannot create jwt with blank userName");
        }
        return JWT.create()
                .withSubject(userName)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    public String getUserNameFromJwt(String jwtToken) {
        return JWT.require(algorithm)
                .build()
                .verify(jwtToken)
                .getSubject();
    }
}
