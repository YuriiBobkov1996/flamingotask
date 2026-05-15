package api.rest.helpers;

import config.TestConfig;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;


public class BaseHelper {
    public RequestSpecification requestSpecification;

    public BaseHelper() {
        requestSpecBuilder();

    }

    public RequestSpecification requestSpecBuilder() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder()
                .setBaseUri(TestConfig.config().apiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addFilter(new AllureRestAssured())
                .log(LogDetail.ALL);
        requestSpecification = requestSpecBuilder.build();
        return requestSpecification;
    }

}
