package com.hellofresh.challenge;

import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.entity.RestRequest;
import com.hellofresh.challenge.entity.RestResponse;
import com.hellofresh.challenge.util.Constants;
import com.hellofresh.challenge.util.builder.BookingBuilder;
import com.hellofresh.challenge.util.builder.RestRequestBuilder;
import com.hellofresh.challenge.util.parser.BookingJsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestPostBookings extends BasicTest {

    @Test
    public void postBooking() {
        restRequestBuilder.setRestMethod(Constants.POST_METHOD);
        restRequestBuilder.setBaseUrl(url);
        restRequestBuilder.setApiMethod(endpoint);
        BookingBuilder bookingBuilder = new BookingBuilder();
        Booking booking = bookingBuilder.buildRandomBooking();
        restRequestBuilder.setBody(booking.toJsonString());
        RestRequest restRequest = restRequestBuilder.build();
        RestResponse restResponse = restResponseFactory.processRestRequest(restRequest);
        Assert.assertEquals("Incorrect response code.",201, restResponse.getCode());
        restRequestBuilder = new RestRequestBuilder();
        restRequestBuilder.setRestMethod(Constants.GET_METHOD);
        restRequestBuilder.setBaseUrl(url);
        restRequestBuilder.setApiMethod(endpoint);
        RestRequest restGetRequest = restRequestBuilder.build();
        RestResponse restGetResponse = restResponseFactory.processRestRequest(restGetRequest);
        BookingJsonParser bookingJsonParser = new BookingJsonParser(restGetResponse.getBody());
        List<Booking> bookingList = bookingJsonParser.getListOfBookings();
        int matches = 0;
        for (Booking receivedBooking: bookingList) {
            if (receivedBooking.equals(booking)) {
                matches++;
            }
        }
        Assert.assertEquals("No matching Booking found for the one created via POST",1, matches);
    }
}
