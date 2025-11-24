package com.example.login.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration //Marks this class as source of beans
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private OauthSuccessHandler handler;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth->{
                    auth.requestMatchers(("/")).permitAll();
                    auth.anyRequest().authenticated();
                })
                .oauth2Login(oauth2 -> oauth2
                        .successHandler(handler)
                        .authorizationEndpoint(a->a
                                .baseUri("/oauth2/authorize"))
                                .defaultSuccessUrl("/secured",true)
                )
                .formLogin(form -> form
                        .successHandler(handler)
                        .permitAll()
                )
                .build();
    }
}
