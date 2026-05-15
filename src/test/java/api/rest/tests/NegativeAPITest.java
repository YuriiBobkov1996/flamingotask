package api.rest.tests;

import api.rest.constants.Endpoints;
import api.rest.helpers.ApiHelper;
import api.rest.models.AuthErrorResponse;
import api.rest.models.AuthRequest;
import api.rest.models.BookingRequest;
import api.rest.models.BookingResponse;
import api.rest.testdata.BookingTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("api rest")
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
                        .statusCode(SC_OK)
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
