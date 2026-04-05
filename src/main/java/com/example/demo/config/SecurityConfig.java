// FIX: SecurityConfig.java

package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable()
            .authorizeHttpRequests(auth -> auth

                // ✅ FRONTEND PAGES (ADD DASHBOARD HERE)
                .requestMatchers(
                        "/",
                        "/login",
                        "/register",
                        "/dashboard",   // 🔥 FIXED
                        "/medicines",
                        "/cart",
                        "/orders",
                        "/admin-dashboard"
                ).permitAll()

                // ✅ STATIC
                .requestMatchers(
                        "/css/**",
                        "/js/**",
                        "/images/**"
                ).permitAll()

                // ✅ APIs
                .requestMatchers(
                        "/api/**"
                ).permitAll()

                // 🔐 OTHERS
                .anyRequest().permitAll()
            )
            .httpBasic().disable();

        return http.build();
    }
}