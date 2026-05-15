package api.rest.tests;

import api.rest.helpers.ApiHelper;
import api.rest.models.BookingRequest;
import api.rest.models.BookingResponse;
import api.rest.testdata.BookingTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("api rest")
public class BookingApiCRUDTest {
    private final ApiHelper apiHelper = new ApiHelper();

    @Test
    @DisplayName("Verify user can create booking")
    @Tag("api rest")
    public void сreateBookingTest() {
        BookingRequest bookingRequest = BookingTestData.defaultBooking();
        BookingResponse bookingResponse = apiHelper.createBooking(bookingRequest);
        assertThat(bookingResponse.getBookingid()).isNotNull();
    }

    @Test
    @DisplayName("Verify user can retrieve booking by id")
    public void getBookingByIdTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        BookingRequest retrievedBooking = apiHelper.getBookingById(createdBookingResponse.getBookingid());
        assertThat(retrievedBooking).usingRecursiveComparison().isEqualTo(bookingToCreate);
    }

    @Test
    @DisplayName("Verify user can update booking by id")
    public void updateBookingByIdTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        BookingRequest bookingToUpdate = BookingTestData.updatedBooking();
        BookingRequest updatedBooking = apiHelper.updateBooking(createdBookingResponse.getBookingid(), bookingToUpdate);
        assertThat(updatedBooking).usingRecursiveComparison().isEqualTo(bookingToUpdate);
    }

    @Test
    @DisplayName("Verify user can delete booking by id")
    public void deleteBookingByIdTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        int bookingId = createdBookingResponse.getBookingid();
        apiHelper.deleteBooking(bookingId);
        apiHelper.verifyBookingNotFound(bookingId);
    }
}
