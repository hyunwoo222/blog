package com.hyun.blog.domain.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64URL {

    private Base64URL(){
    }

    public static String encodeFromString(String rawString) {
        return encodeFromBytes(rawString.getBytes(StandardCharsets.UTF_8));
    }

    private static String encodeFromBytes(byte[] rawBytes) {
        return Base64.getUrlEncoder().withoutPadding()
                .encodeToString(rawBytes);
    }
}
