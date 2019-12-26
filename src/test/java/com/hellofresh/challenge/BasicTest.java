package com.hellofresh.challenge;

import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.entity.RestRequest;
import com.hellofresh.challenge.entity.RestResponse;
import com.hellofresh.challenge.factory.RestResponseFactory;
import com.hellofresh.challenge.util.builder.BookingBuilder;
import com.hellofresh.challenge.util.builder.RestRequestBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class BasicTest {

    protected RestRequestBuilder restRequestBuilder = new RestRequestBuilder();

    protected final RestResponseFactory restResponseFactory = new RestResponseFactory();

    @Test
    public void dummyGetTest() {
        restRequestBuilder.setRestMethod("GET");
        restRequestBuilder.setBaseUrl("https://automationintesting.online/");
        restRequestBuilder.setApiMethod("booking/");
        RestRequest restRequest = restRequestBuilder.build();
        RestResponse restResponse = restResponseFactory.processRestRequest(restRequest);
        log.info("restResponse = {}", restResponse);
    }

    @Test
    public void dummyPostTest() {
        restRequestBuilder.setRestMethod("POST");
        restRequestBuilder.setBaseUrl("https://automationintesting.online/");
        restRequestBuilder.setApiMethod("booking/");
        BookingBuilder bookingBuilder = new BookingBuilder();
        bookingBuilder.setBookingId(4);
        bookingBuilder.setCheckInDate("2019-12-26T14:24:37.029Z");
        bookingBuilder.setCheckOutDate("2019-12-29T14:25:37.029Z");
        bookingBuilder.setDepositPaid(true);
        bookingBuilder.setEmail("john.wick@killers.com");
        bookingBuilder.setFirstName("John");
        bookingBuilder.setLastName("Wick");
        bookingBuilder.setPhoneNumber("+11002345121132");
        bookingBuilder.setRoomId(4);
        Booking booking = bookingBuilder.buildBooking();
        restRequestBuilder.setBody(booking.toJsonString());
//        restRequestBuilder.setBody("{\n" +
//                "  \"bookingdates\": {\n" +
//                "    \"checkin\": \"2019-12-26T14:24:37.029Z\",\n" +
//                "    \"checkout\": \"2019-12-29T14:25:37.029Z\"\n" +
//                "  },\n" +
//                "  \"bookingid\": 19,\n" +
//                "  \"depositpaid\": true,\n" +
//                "  \"email\": \"john.wick@killers.com\",\n" +
//                "  \"firstname\": \"John\",\n" +
//                "  \"lastname\": \"Wick\",\n" +
//                "  \"phone\": \"+11002345121132\",\n" +
//                "  \"roomid\": 4\n" +
//                "}");
        RestRequest restRequest = restRequestBuilder.build();
        RestResponse restResponse = restResponseFactory.processRestRequest(restRequest);
        log.info("restResponse = {}", restResponse);
    }
}
