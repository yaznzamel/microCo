package com.yaznzamel.microco.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()  // Allow ALL requests without authentication
            )
            .formLogin(login -> login.disable())  // Disable login page
            .httpBasic(basic -> basic.disable()); // Disable Basic Auth

        return http.build();
    }
}
