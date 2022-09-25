package com.hyun.blog.domain.jwt;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.hyun.blog.domain.jwt.Base64URL.encodeFromString;
import static org.hamcrest.MatcherAssert.assertThat;



class Base64URLTest {

    @Test
    void when_encode_return_expected_string () {
        Assertions.assertThat(encodeFromString("something")).isEqualTo("c29tZXRoaW5n");
    }

}