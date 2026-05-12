package api.tests;

import api.constants.Endpoints;
import api.helpers.ApiHelper;
import api.models.BookingRequest;
import api.models.BookingResponse;
import api.testdata.BookingTestData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ValidationAPITest {
    private final ApiHelper apiHelper = new ApiHelper();
    @Test
    @DisplayName("Verify booking response schema")
    public void bookingSchemaValidationTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        given()
                .spec(apiHelper.requestSpecification)
                .body(bookingToCreate)
                .when()
                .post(Endpoints.BOOKING_ENDPOINT)
                .then()
                .log().all()
                .statusCode(SC_OK)
                .body(matchesJsonSchemaInClasspath("schemas/booking-schema.json"));
    }

    @Test
    @DisplayName("Verify booking response contains required fields")
    public void requiredFieldsValidationTest() {
        BookingRequest expectedBooking = BookingTestData.defaultBooking();
        BookingResponse actualBookingResponse = apiHelper.createBooking(expectedBooking);
        assertThat(actualBookingResponse.getBooking()).usingRecursiveComparison().isEqualTo(expectedBooking);
    }

    @Test
    @DisplayName("Verify booking dates have valid format")
    public void dateFormatValidationTest() {
        BookingRequest bookingToCreate = BookingTestData.defaultBooking();
        BookingResponse createdBookingResponse = apiHelper.createBooking(bookingToCreate);
        assertThat(createdBookingResponse.getBooking().getBookingdates().getCheckin()).matches("\\d{4}-\\d{2}-\\d{2}");
        assertThat(createdBookingResponse.getBooking().getBookingdates().getCheckout()).matches("\\d{4}-\\d{2}-\\d{2}");
    }
}
