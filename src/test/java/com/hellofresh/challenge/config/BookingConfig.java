package com.hellofresh.challenge.config;

import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.util.parser.BookingJsonParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:properties/bookings.properties")
public class BookingConfig {
    @Value("${booking1}")
    private String booking1JsonString;

    @Value("${booking2}")
    private String booking2JsonString;

    @Value("${booking3}")
    private String booking3JsonString;

    @Bean
    public Booking booking1() {
        return new BookingJsonParser(booking1JsonString).getBooking();
    }

    @Bean
    public Booking booking2() {
        return new BookingJsonParser(booking2JsonString).getBooking();
    }

    @Bean
    public Booking booking3() {
        return new BookingJsonParser(booking3JsonString).getBooking();
    }
}
