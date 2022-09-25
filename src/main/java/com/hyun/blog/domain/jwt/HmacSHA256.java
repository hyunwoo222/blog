package com.hyun.blog.domain.jwt;

import lombok.var;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class HmacSHA256 {

    private static final String ALGRITHM = "HmacSHA256";

    private HmacSHA256() {
    }

    public static byte[] sign(byte[] secret,String message) {
        try{
            final var hmacSHA256 = Mac.getInstance(ALGRITHM);
            hmacSHA256.init(new SecretKeySpec(secret,ALGRITHM));
            return hmacSHA256.doFinal(message.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException | IllegalArgumentException | InvalidKeyException exception) {
            throw new HmacSHA256SignFailedException(exception);
        }

}

    private static class HmacSHA256SignFailedException extends RuntimeException {
        public HmacSHA256SignFailedException(Throwable cause) {
            super(cause);
        }
        }
    }
