package com.jincrates.cogito.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class PasswordTests {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testEncode() {

        String password = "1111";

        String enPwd = passwordEncoder.encode(password);

        System.out.println("enPwd : " + enPwd);

        boolean matchResult = passwordEncoder.matches(password, enPwd);

        System.out.println("matchResult : " + matchResult);
    }
}