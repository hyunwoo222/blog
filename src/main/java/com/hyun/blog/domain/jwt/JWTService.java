package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.User;

public interface JWTService {

    String generateTokenFromUser(User user);
}
