package com.hellofresh.challenge.util.builder;

import com.hellofresh.challenge.entity.Booking;
import com.hellofresh.challenge.util.DateUtil;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Setter
@NoArgsConstructor
public class BookingBuilder {

    private static final String[] FIRST_NAMES_LIST = {"John", "Steeve", "Bob", "Sarah", "Ben", "Tony", "Olga"};
    private static final String[] LAST_NAMES_LIST = {"Black", "Wick", "Brown", "Konnor", "Kark", "Freud", "Konovalova"};
    private static final String[] EMAIL_DOMAINS = {"gmail.com", "yandex.ru", "yahoo.com", "tut.by"};

    private int bookingId;
    private String checkInDate;
    private String checkOutDate;
    private boolean depositPaid;
    private String email;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private int roomId;

    public Booking buildBooking() {
        return new Booking(bookingId, checkInDate, checkOutDate, depositPaid, email, firstName, lastName, phoneNumber, roomId);
    }

    public Booking buildRandomBooking() {
        Random random = new Random();
        bookingId = random.nextInt(20);
        roomId = random.nextInt(10);
        int range = random.nextInt(30);
        int duration = random.nextInt(10);
        DateUtil dateUtil = new DateUtil();
        checkInDate = dateUtil.getFutureDate(range);
        checkOutDate = dateUtil.getFutureDate(range+duration);
        depositPaid = true;
        int firstNameIdx = random.nextInt(FIRST_NAMES_LIST.length-1);
        firstName = FIRST_NAMES_LIST[firstNameIdx];
        int lastNameIdx = random.nextInt(LAST_NAMES_LIST.length-1);
        lastName = LAST_NAMES_LIST[lastNameIdx];
        int domainIdx = random.nextInt(EMAIL_DOMAINS.length-1);
        String emailDomain = EMAIL_DOMAINS[domainIdx];
        email = firstName+"."+lastName+"@"+emailDomain;
        phoneNumber = "+12345678123123";
        return buildBooking();
    }
}
