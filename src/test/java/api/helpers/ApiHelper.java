package api.helpers;

import api.constants.Endpoints;
import api.models.*;
import io.restassured.response.Response;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ApiHelper extends BaseHelper {

    public AuthResponse getToken(AuthRequest authRequest) {
        return given().spec(requestSpecification)
                .body(authRequest)
                .when()
                .post(Endpoints.AUTH_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200)
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
                .statusCode(200)
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
                .statusCode(200)
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
                .statusCode(200)
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
                .statusCode(201);
    }

    public void verifyBookingNotFound(int id) {
        given().spec(requestSpecification)
                .pathParam("id", id)
                .when()
                .get(Endpoints.BOOKING_BY_ID_ENDPOINT)
                .then()
                .log().all()
                .statusCode(404);
    }

    public List<BookingIdResponse> getAllIds() {
        return given().spec(requestSpecification)
                .when()
                .get(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(200)
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
                .statusCode(200)
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
                .statusCode(200)
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
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        return getToken(authRequest).getToken();
    }
}
