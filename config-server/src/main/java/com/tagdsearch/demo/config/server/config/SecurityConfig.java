package com.tagdsearch.demo.config.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/encrypt/**", "/decrypt/**","/**").permitAll() // Allow unauthenticated access
                        .anyRequest().authenticated() // Require authentication for other requests
                )
                .csrf(csrf -> csrf.disable()); // Disable CSRF protection if necessary

        return http.build();
    }

    // This method configures web security, no need for @Bean annotation
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .requestMatchers("/encrypt/**", "/decrypt/**"); // Ignore these paths
    }
}