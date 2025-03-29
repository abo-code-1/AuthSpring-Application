package com.example.AuthSpring_Application.config;

import com.example.AuthSpring_Application.models.Role;
import com.example.AuthSpring_Application.models.User;
import com.example.AuthSpring_Application.repository.UserRepository;
import com.example.AuthSpring_Application.services.AuthenticationService;
import com.example.AuthSpring_Application.services.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.example.AuthSpring_Application.models.AuthProvider.GOOGLE;

@Service
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        @NonNull Authentication authentication
                                        ) throws ServletException, IOException {

        DefaultOAuth2User oAuthUser = (DefaultOAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuthUser.getAttributes();

        String user_email = (String) attributes.get("email");
        String user_name = (String) attributes.get("name");

        String user_firstName = (String) attributes.get("given_name");
        String user_lastName = (String) attributes.get("family_name");


        var user = userRepository.findByEmail(user_email).orElse(null);

        if(user == null) {
            user = User.builder()
                    .firstname(user_firstName)
                    .lastname(user_lastName)
                    .email(user_email)
                    .build();
            userRepository.save(user);
        }

        // Перенаправляем на страницу после входа
        response.sendRedirect("/auth/dashboard");
    }
}
