package api.tests;

import api.constants.Endpoints;
import api.helpers.ApiHelper;
import api.models.AuthErrorResponse;
import api.models.AuthRequest;
import api.models.BookingRequest;
import api.models.BookingResponse;
import api.testdata.BookingTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class NegativeAPITest {
    private final ApiHelper apiHelper = new ApiHelper();
    private static final int NON_EXISTENT_BOOKING_ID = Integer.MAX_VALUE;

    @Test
    @DisplayName("Verify user cannot authenticate with invalid credentials")
    public void invalidAuthenticationTest() {

        AuthRequest invalidAuthRequest = new AuthRequest("wrongUser", "wrongPassword");
        AuthErrorResponse response =
                given().spec(apiHelper.requestSpecification)
                        .body(invalidAuthRequest)
                        .when()
                        .post(Endpoints.AUTH_ENDPOINT)
                        .then()
                        .log().all()
                        .statusCode(200)
                        .extract()
                        .as(AuthErrorResponse.class);
        assertThat(response.getReason()).isEqualTo("Bad credentials");
    }

    @Test
    @DisplayName("Verify user cannot update booking without token")
    public void updateBookingWithoutTokenTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        BookingRequest bookingForUpdate = BookingTestData.updatedBooking();
        Response response = apiHelper.updateBookingWithoutToken(createdBookingResponse.getBookingid(), bookingForUpdate);
        assertThat(response.statusCode()).isEqualTo(SC_FORBIDDEN);
    }

    @Test
    @DisplayName("Verify user cannot get non-existent booking")
    public void getNonExistentBookingTest() {
        Response response = apiHelper.getNonExistentBooking(NON_EXISTENT_BOOKING_ID);
        assertThat(response.statusCode()).isEqualTo(SC_NOT_FOUND);
    }
}
