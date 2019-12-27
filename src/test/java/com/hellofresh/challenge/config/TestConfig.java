package com.hellofresh.challenge.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(BookingConfig.class)
@PropertySource("classpath:properties/test.properties")
public class TestConfig {
    @Value("${rest.url}")
    private String url;

    @Value("${rest.endpoint}")
    private String endpoint;

    @Bean
    public String url() {
        return url;
    }

    @Bean
    public String endpoint() {
        return endpoint;
    }
}
