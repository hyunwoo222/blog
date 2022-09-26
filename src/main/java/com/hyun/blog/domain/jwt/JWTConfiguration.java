package com.hyun.blog.domain.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfiguration {

    private static final long DURATION_SECONDS = 800;
    private static final String SECRET = "SECRET_FOR_TOKEN";

    @Bean
    JWTService jwtService() {
        return new HS256JWTService(SECRET,DURATION_SECONDS);
    }
}
