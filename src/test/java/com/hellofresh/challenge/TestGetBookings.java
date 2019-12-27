package com.hellofresh.challenge;

import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.entity.RestRequest;
import com.hellofresh.challenge.entity.RestResponse;
import com.hellofresh.challenge.util.Constants;
import com.hellofresh.challenge.util.parser.BookingJsonParser;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

@Slf4j
public class TestGetBookings extends BasicTest {

    @Test
    public void bookingsCountTest() {
        restRequestBuilder.setRestMethod(Constants.GET_METHOD);
        restRequestBuilder.setBaseUrl(url);
        restRequestBuilder.setApiMethod(endpoint);
        RestRequest restRequest = restRequestBuilder.build();
        RestResponse restResponse = restResponseFactory.processRestRequest(restRequest);
        BookingJsonParser bookingJsonParser = new BookingJsonParser(restResponse.getBody());
        List<Booking> bookingList = bookingJsonParser.getListOfBookings();
        Assert.assertTrue("The amount of the existing bookings is less than expected",bookingList.size() > 2);
    }

    @Test
    public void testExistingBookingsMatch() {
        restRequestBuilder.setRestMethod(Constants.GET_METHOD);
        restRequestBuilder.setBaseUrl(url);
        restRequestBuilder.setApiMethod(endpoint);
        RestRequest restRequest = restRequestBuilder.build();
        RestResponse restResponse = restResponseFactory.processRestRequest(restRequest);
        BookingJsonParser bookingJsonParser = new BookingJsonParser(restResponse.getBody());
        List<Booking> bookingList = bookingJsonParser.getListOfBookings();
        int matches = 0;
        Booking booking1 = context.getBean("booking1", Booking.class);
        Booking booking2 = context.getBean("booking2", Booking.class);
        Booking booking3 = context.getBean("booking3", Booking.class);
        for (Booking receivedBooking: bookingList) {
            if (receivedBooking.equals(booking1) || receivedBooking.equals(booking2) || receivedBooking.equals(booking3)) {
                matches++;
            }
        }
        Assert.assertEquals("Not all of the existing bookings were found",3, matches);
    }
}
