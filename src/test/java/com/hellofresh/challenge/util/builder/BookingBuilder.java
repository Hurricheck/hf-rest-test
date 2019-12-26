package com.hellofresh.challenge.util.builder;

import com.hellofresh.challenge.entity.Booking;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class BookingBuilder {
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
}
