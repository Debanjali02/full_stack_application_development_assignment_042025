package com.student.vaccine.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Allows CORS for all endpoints
                .allowedOrigins("http://localhost:3000")  // Your React app URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Allowed HTTP methods
                .allowedHeaders("*")  // Allows all headers
                .allowCredentials(true);  // Allows cookies to be sent
    }
}
