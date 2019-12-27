package com.hellofresh.challenge.util.parser;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.util.builder.BookingBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BookingJsonParser {
    private static final String BOOKINGS_ARRAY_ELEMENT = "bookings";
    private static final String BOOKING_DATES_ELEMENT = "bookingdates";
    private static final String CHECK_IN_ELEMENT = "checkin";
    private static final String CHECK_OUT_ELEMENT = "checkout";
    private static final String BOOKING_ID_ELEMENT = "bookingid";
    private static final String DEPOSIT_PAID_ELEMENT = "depositpaid";
    private static final String FIRSTNAME_ELEMENT = "firstname";
    private static final String LASTNAME_ELEMENT = "lastname";
    private static final String ROOMID_ELEMENT = "roomid";

    private final String stringToParse;

    public BookingJsonParser(String stringToParse) {
        this.stringToParse = stringToParse;
    }

    public List<Booking> getListOfBookings() {
        log.info("Parsing string = {} into the array of Booking elements", stringToParse);
        List<Booking> bookingsList = new ArrayList<>();
        JsonArray jsonArray = new JsonParser().parse(stringToParse)
                .getAsJsonObject()
                .getAsJsonArray(BOOKINGS_ARRAY_ELEMENT);
        for (int i = 0; i < jsonArray.size(); i++) {
            JsonObject bookingObject = (JsonObject) jsonArray.get(i);
            log.info("Processing {} element of array", bookingObject);
            bookingsList.add(getBookingFromJsonObject(bookingObject));
        }
        return bookingsList;
    }

    private Booking getBookingFromJsonObject(JsonObject bookingObject) {
        BookingBuilder bookingBuilder = new BookingBuilder();
        bookingBuilder.setBookingId(bookingObject.get(BOOKING_ID_ELEMENT).getAsInt());
        JsonObject bookingDatesObject = bookingObject.get(BOOKING_DATES_ELEMENT).getAsJsonObject();
        bookingBuilder.setCheckInDate(bookingDatesObject.get(CHECK_IN_ELEMENT).getAsString());
        bookingBuilder.setCheckOutDate(bookingDatesObject.get(CHECK_OUT_ELEMENT).getAsString());
        bookingBuilder.setDepositPaid(bookingObject.get(DEPOSIT_PAID_ELEMENT).getAsBoolean());
        bookingBuilder.setEmail("");
        bookingBuilder.setFirstName(bookingObject.get(FIRSTNAME_ELEMENT).getAsString());
        bookingBuilder.setLastName(bookingObject.get(LASTNAME_ELEMENT).getAsString());
        bookingBuilder.setPhoneNumber("");
        bookingBuilder.setRoomId(bookingObject.get(ROOMID_ELEMENT).getAsInt());
        return bookingBuilder.buildBooking();
    }

    public Booking getBooking() {
        return getBookingFromJsonObject(new JsonParser().parse(stringToParse).getAsJsonObject());
    }
}
