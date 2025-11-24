package com.example.login.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OauthSuccessHandler implements AuthenticationSuccessHandler {
    Logger logger = LoggerFactory.getLogger(OauthSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthenticationSuccessHandler");

        DefaultOAuth2User user = (DefaultOAuth2User)authentication.getPrincipal();
        logger.info(user.getName());
        user.getAttributes().forEach((key,value) -> {
            logger.info("{} -> {}", key, value);
        });
        logger.info(user.getAuthorities().toString());

        response.sendRedirect("/secured");
    }
}
