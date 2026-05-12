package api.tests;

import api.helpers.ApiHelper;
import api.models.BookingIdResponse;
import api.models.BookingRequest;
import api.models.BookingResponse;
import api.testdata.BookingTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SearchAndFilterTest {
    private final ApiHelper apiHelper = new ApiHelper();

    @Test
    @DisplayName("Verify user can get all booking ids")
    public void getAllBookingsTest() {
        List<BookingIdResponse> bookings = apiHelper.getAllIds();
        assertThat(bookings).isNotEmpty();
        assertThat(bookings).extracting(BookingIdResponse::getBookingid).doesNotContainNull();
    }

    @Test
    @DisplayName("Verify user can filter bookings by firstname")
    public void getBookingsByFirstnameTest() {

        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        List<BookingIdResponse> filteredBookings = apiHelper.filterBookingsByFirstname(bookingToCreate.getFirstname());
        boolean bookingExists = filteredBookings.stream().anyMatch(booking -> booking.getBookingid()
                .equals(createdBookingResponse.getBookingid()));
        assertThat(bookingExists).isTrue();
    }

    @Test
    @DisplayName("Verify user can filter bookings by dates")
    public void getBookingsByDatesTest() {
        List<BookingIdResponse> filteredBookings = apiHelper.filterBookingsByDates("2026-01-01", "2026-01-05");
        assertThat(filteredBookings).isNotEmpty();
        assertThat(filteredBookings).allSatisfy(booking -> assertThat(booking.getBookingid()).isNotNull());
    }
}
