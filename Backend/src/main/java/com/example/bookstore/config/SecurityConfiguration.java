package com.example.bookstore.config;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.Collections;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final AuthenticationProvider authenticationProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
        http = http.cors().and().csrf().disable();

        // Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
        http = http
                .exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage()))
                .and();

        // Set permissions on endpoints

        http.authorizeRequests()
                // Our public endpoints
//                .requestMatchers("/api/**").permitAll();
                .requestMatchers("/resources/**").permitAll()
                .requestMatchers("/templates/**").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/js/**").permitAll()
                .requestMatchers("/img/**").permitAll()
                .requestMatchers("/xml/**").permitAll()
//                .requestMatchers("/images/**").permitAll()

                .requestMatchers("/api/auth/**", "/images/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/products/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/authors/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/categories/**").permitAll()

                // Our private endpoints
                .requestMatchers(HttpMethod.GET, "/api/orders", "/api/users").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/api/product/", "/api/authors", "/api/users/user/{id}/disable",
                        "/api/users/user/{id}/enable", "/api/categories").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/products/{id}", "/api/order/{id}", "/api/authors/{id}",
                        "/api/categories/{id}").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/products/{id}", "/api/users/{id}", "/api/authors/{id}",
                        "/api/categories/{id}").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/api/orders/{id}", "/api/users/user", "/api/users/{id}",
                        "/api/users/cart", "/api/users/order").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.POST, "/api/orders", "/api/users/cart/{id}").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.PUT, "/api/users").hasAnyRole("ADMIN", "CUSTOMER")
                .requestMatchers(HttpMethod.DELETE, "/api/order/{id}", "/api/users/cart/{id}").hasAnyRole("ADMIN", "CUSTOMER")

                .anyRequest().authenticated();

        // Add JWT token filter
        http.authenticationProvider(authenticationProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
