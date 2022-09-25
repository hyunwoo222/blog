package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.User;

public class JWTPayload {

    private final long sub;
    private final String name;
    private final long iat;

    public JWTPayload(long sub, String name, long iat) {
        this.sub = sub;
        this.name = name;
        this.iat = iat;
    }


    static JWTPayload fromUser(User user, long expireEpochSecond) {
        return new JWTPayload(user.getId(),user.getUsername(),expireEpochSecond);
    }

    @Override
    public String toString() {
        return "JWTPayload{" +
                "sub=" + sub +
                ", name='" + name + '\'' +
                ", iat=" + iat +
                '}';
    }
}
