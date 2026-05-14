package api.rest.helpers;

import api.rest.constants.Endpoints;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseHelper {
    public RequestSpecification requestSpecification;
    public ResponseSpecification responseSpecification;

    public BaseHelper() {
        requestSpecBuilder();
        responseSpecBuilder();
    }

    public RequestSpecification requestSpecBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(Endpoints.BASE_URL)
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }

    public ResponseSpecification responseSpecBuilder() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
        return responseSpecification;
    }
}
