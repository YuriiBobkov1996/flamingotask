package api.rest.helpers;
import api.rest.constants.Endpoints;
import api.rest.models.*;
import api.rest.testdata.AuthTestData;
import io.restassured.response.Response;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.*;

public class ApiHelper extends BaseHelper {

    public AuthResponse getToken(AuthRequest authRequest) {
        return given().spec(requestSpecification)
                .body(authRequest)
                .when()
                .post(Endpoints.AUTH_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(AuthResponse.class);
    }

    public BookingResponse createBooking(BookingRequest bookingRequest) {
        return given().spec(requestSpecification)
                .body(bookingRequest)
                .when()
                .post(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(BookingResponse.class);
    }

    public BookingRequest getBookingById(int id) {
        return given().spec(requestSpecification)
                .pathParam("id", id)
                .when()
                .get(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(BookingRequest.class);
    }

    public BookingRequest updateBooking(int id, BookingRequest bookingRequest) {
        return given().spec(requestSpecification)
                .pathParam("id", id)
                .cookie("token", getAuthToken())
                .body(bookingRequest)
                .when()
                .put(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .as(BookingRequest.class);
    }

    public void deleteBooking(int id) {
        given().spec(requestSpecification)
                .pathParam("id", id)
                .cookie("token", getAuthToken())
                .when()
                .delete(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_CREATED);
    }

    public void verifyBookingNotFound(int id) {
        given().spec(requestSpecification)
                .pathParam("id", id)
                .when()
                .get(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_NOT_FOUND);
    }

    public List<BookingIdResponse> getAllIds() {
        return given().spec(requestSpecification)
                .when()
                .get(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getList("", BookingIdResponse.class);
    }

    public List<BookingIdResponse> filterBookingsByFirstname(String firstname) {
        return given().spec(requestSpecification)
                .queryParam("firstname", firstname)
                .when()
                .get(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getList("", BookingIdResponse.class);
    }

    public List<BookingIdResponse> filterBookingsByDates(String checkin, String checkout) {
        return given().spec(requestSpecification)
                .queryParam("checkin", checkin)
                .queryParam("checkout", checkout)
                .when()
                .get(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .extract()
                .jsonPath()
                .getList("", BookingIdResponse.class);
    }

    public Response updateBookingWithoutToken(int id, BookingRequest bookingRequest) {
        return given().spec(requestSpecification)
                .pathParam("id", id)
                .body(bookingRequest)
                .when()
                .put(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    public Response getNonExistentBooking(int id) {
        return given().spec(requestSpecification)
                .pathParam("id", id)
                .when()
                .get(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .extract()
                .response();
    }

    private String getAuthToken() {
        return getToken(AuthTestData.validAuthRequest()).getToken();
    }
}
