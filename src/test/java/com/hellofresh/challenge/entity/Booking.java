package com.hellofresh.challenge.entity;

import lombok.Getter;
import org.apache.commons.lang3.text.StrSubstitutor;

import java.util.HashMap;
import java.util.Map;

@Getter
public class Booking {
    private final static String BOOKING_ID_ALIAS = "bookingId";
    private final int bookingId;
    private final static String CHECK_IN_DATE_ALIAS = "checkInDate";
    private final String checkInDate;
    private final static String CHECK_OUT_DATE_ALIAS = "checkOutDate";
    private final String checkOutDate;
    private final static String DEPOSIT_PAID_ALIAS = "depositPaid";
    private final boolean depositPaid;
    private final static String EMAIL_ALIAS = "email";
    private final String email;
    private final static String FIRSTNAME_ALIAS = "firstName";
    private final String firstName;
    private final static String LASTNAME_ALIAS = "lastName";
    private final String lastName;
    private final static String PHONENUMBER_ALIAS = "phoneNumber";
    private final String phoneNumber;
    private final static String ROOMID_ALIAS = "roomId";
    private final int roomId;

    private final static String jsonFormat = "{\n" +
            "  \"bookingdates\": {\n" +
            "    \"checkin\": \"${"+CHECK_IN_DATE_ALIAS+"}\",\n" +
            "    \"checkout\": \"${"+CHECK_OUT_DATE_ALIAS+"}\"\n" +
            "  },\n" +
            "  \"bookingid\": ${"+BOOKING_ID_ALIAS+"},\n" +
            "  \"depositpaid\": ${"+DEPOSIT_PAID_ALIAS+"},\n" +
            "  \"email\": \"${"+EMAIL_ALIAS+"}\",\n" +
            "  \"firstname\": \"${"+FIRSTNAME_ALIAS+"}\",\n" +
            "  \"lastname\": \"${"+LASTNAME_ALIAS+"}\",\n" +
            "  \"phone\": \"${"+PHONENUMBER_ALIAS+"}\",\n" +
            "  \"roomid\": ${"+ROOMID_ALIAS+"}\n" +
            "}";

    public Booking(int bookingId, String checkInDate, String checkOutDate, boolean depositPaid, String email, String firstName, String lastName, String phoneNumber, int roomId) {
        this.bookingId = bookingId;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.depositPaid = depositPaid;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.roomId = roomId;
    }

    public String toJsonString() {
        Map<String, String> values = new HashMap();
        values.put(BOOKING_ID_ALIAS, String.valueOf(bookingId));
        values.put(CHECK_IN_DATE_ALIAS, checkInDate);
        values.put(CHECK_OUT_DATE_ALIAS, checkOutDate);
        values.put(DEPOSIT_PAID_ALIAS, String.valueOf(depositPaid));
        values.put(EMAIL_ALIAS, email);
        values.put(FIRSTNAME_ALIAS, firstName);
        values.put(LASTNAME_ALIAS, lastName);
        values.put(PHONENUMBER_ALIAS, phoneNumber);
        values.put(ROOMID_ALIAS, String.valueOf(roomId));
        StrSubstitutor strSubstitutor = new StrSubstitutor(values);
        return strSubstitutor.replace(jsonFormat);
    }
}
