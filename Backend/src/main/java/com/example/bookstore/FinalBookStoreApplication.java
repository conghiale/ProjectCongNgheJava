package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FinalBookStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinalBookStoreApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/api/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/products").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/products/author/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/products/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/auth/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/auth/login").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/auth/register").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/users/user").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/users/user/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/users/user/disable/*").allowedOrigins("*").allowedMethods("POST","DELETE","GET","PUT");
                registry.addMapping("/api/users/user/enable/*").allowedOrigins("*").allowedMethods("POST","DELETE","GET","PUT");
                registry.addMapping("/api/users").allowedOrigins("*").allowedMethods("PUT");
                registry.addMapping("/api/users/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/users").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/authors").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/authors/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("*");
                registry.addMapping("/api/categories/*").allowedOrigins("*").allowedHeaders("*").allowedMethods("POST","DELETE","GET","PUT");
                registry.addMapping("/api/categories").allowedOrigins("*").allowedHeaders("*").allowedMethods("POST","DELETE","GET","PUT");
            }
        };
    }
}
