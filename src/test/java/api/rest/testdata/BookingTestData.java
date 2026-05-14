package api.rest.testdata;

import api.rest.models.BookingDates;
import api.rest.models.BookingRequest;
import com.github.javafaker.Faker;

public class BookingTestData {
    private static final Faker faker = new Faker();
    public static BookingRequest defaultBooking() {
        return BookingRequest.builder()
                .firstname(generateUniqueFirstname())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 1000))
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
                .firstname(generateUniqueFirstname())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 1000))
                .depositpaid(false)
                .bookingdates(BookingDates.builder()
                        .checkin("2026-02-01")
                        .checkout("2026-02-10")
                        .build())
                .additionalneeds("Dinner")
                .build();
    }

    private static String generateUniqueFirstname() {
        return "TestUser" + System.currentTimeMillis();
    }

    private BookingTestData() {
    }
}
