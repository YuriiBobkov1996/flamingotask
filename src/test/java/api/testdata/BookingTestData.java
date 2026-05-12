package api.testdata;

import api.models.BookingDates;
import api.models.BookingRequest;

public class BookingTestData {
    public static BookingRequest defaultBooking() {
        return BookingRequest.builder()
                .firstname("John")
                .lastname("Dou")
                .totalprice(100)
                .depositpaid(true)
                .bookingdates(defaultBookingDates())
                .additionalneeds("Breakfast")
                .build();
    }

    public static BookingDates defaultBookingDates() {
        return BookingDates.builder()
                .checkin("2026-01-01")
                .checkout("2026-01-05")
                .build();
    }

    public static BookingRequest updatedBooking() {
        return BookingRequest.builder()
                .firstname("Jane")
                .lastname("Smith")
                .totalprice(250)
                .depositpaid(false)
                .bookingdates(BookingDates.builder()
                        .checkin("2026-02-01")
                        .checkout("2026-02-10")
                        .build())
                .additionalneeds("Dinner")
                .build();
    }

    private BookingTestData() {
    }
}
