package com.hyun.blog.domain.jwt;

import com.hyun.blog.domain.User;

import java.nio.charset.StandardCharsets;

import static java.time.Instant.now;

public class HS256JWTService implements JWTService{

    private static final String BASE64URL_ENCODED_HEADER = Base64URL.encodeFromString("{\"alg\":\"HS256\",\"type\":\"JWT\"}");

    private final byte[] secret;
    private final long durationSeconds;

    public HS256JWTService(String secret,long durationSeconds) {
        this.secret = secret.getBytes(StandardCharsets.UTF_8);
        this.durationSeconds = durationSeconds;
    }

    @Override
    public String generateTokenFromUser(User user) {
        String messageTosign = BASE64URL_ENCODED_HEADER + "." + base64EncodePayLoadFromUser(user);
        byte[]  signature = HmacSHA256.sign(secret,messageTosign);
        return messageTosign + "." + Base64URL.encodeFromString(signature.toString());
    }

    private String base64EncodePayLoadFromUser(User user) {
        return Base64URL.encodeFromString(JWTPayload.fromUser(user,now().getEpochSecond() + durationSeconds).toString());
    }
}
