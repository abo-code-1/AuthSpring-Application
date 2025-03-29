package com.example.AuthSpring_Application.providers;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor

public class GoogleOAuthConfig {
    private final Dotenv dotenv;
    
    public String getClientId() {
        return dotenv.get("GOOGLE_CLIENT_ID");
    }

    public String getClientSecret() {
        return dotenv.get("GOOGLE_CLIENT_SECRET");
    }
}
