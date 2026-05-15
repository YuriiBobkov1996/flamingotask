package api.rest.testdata;

import api.rest.models.BookingDates;
import api.rest.models.BookingRequest;
import com.github.javafaker.Faker;

public class BookingTestData {

    private static final Faker faker = new Faker();
    public static final String DEFAULT_CHECKIN_DATE = "2026-01-01";
    public static final String DEFAULT_CHECKOUT_DATE = "2026-01-05";
    public static final String UPDATED_CHECKIN_DATE = "2026-02-01";
    public static final String UPDATED_CHECKOUT_DATE = "2026-02-10";

    private BookingTestData() {
    }

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
                .checkin(DEFAULT_CHECKIN_DATE)
                .checkout(DEFAULT_CHECKOUT_DATE)
                .build();
    }

    public static BookingRequest updatedBooking() {
        return BookingRequest.builder()
                .firstname(generateUniqueFirstname())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().numberBetween(100, 1000))
                .depositpaid(false)
                .bookingdates(updatedBookingDates())
                .additionalneeds("Dinner")
                .build();
    }

    public static BookingDates updatedBookingDates() {
        return BookingDates.builder()
                .checkin(UPDATED_CHECKIN_DATE)
                .checkout(UPDATED_CHECKOUT_DATE)
                .build();
    }

    private static String generateUniqueFirstname() {
        return "TestUser" + System.currentTimeMillis();
    }
}
