package com.smunity.api.global.config.security.encoder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class Pbkdf2PasswordEncoderTest {

    @Test
    void encodeTest() {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        String password = pbkdf2PasswordEncoder.encode("testtest");
        System.out.println(password);
        System.out.println(pbkdf2PasswordEncoder.matches("testtest", password));
    }

    @Test
    void matchTest() {
        Pbkdf2PasswordEncoder pbkdf2PasswordEncoder = new Pbkdf2PasswordEncoder();
        String encoding = "pbkdf2_sha256$390000$8ZcmAeOFPm4ZY2aLYghxzI$ufspbGraVRYQIv+TrdJDH5jj2V3aMbz+wXx3qgJ8gbE=";
        System.out.println(pbkdf2PasswordEncoder.matches("testtest", encoding));
    }
}
