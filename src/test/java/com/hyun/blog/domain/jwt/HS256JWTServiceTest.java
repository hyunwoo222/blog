package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HS256JWTServiceTest {

    private String secret = "Hello";
    private long durationSeconds = 60L;

    @Test
    public void generateTokenFromUserTest() {
        HS256JWTService hs256JWTService = new HS256JWTService(secret,durationSeconds);
        User user = new User(1L,"hello","hello@world","world");
        String token = hs256JWTService.generateTokenFromUser(user);
        System.out.println(token);
    }

}