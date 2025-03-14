package com.example.AuthSpring_Application.services;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;

@Service
@PropertySource("classpath:application.properties")


public class SecretKeyProvider {
    @Value("${SECRET_KEY}")
    private String secretKey;

    public String getSecretKey() {
        return secretKey;
    }
}
