package api.graphql.clients;

import api.graphql.models.GraphqlQuery;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

import static api.graphql.helpers.GraphqlUtils.readGql;

import static io.restassured.RestAssured.given;


public class GraphqlClient {

    private final String url;

    private final RequestSpecification requestSpecification = given()
            .contentType(ContentType.JSON)
            .filter(new AllureRestAssured())
            .filters(new RequestLoggingFilter(), new ResponseLoggingFilter());

    public GraphqlClient(String url) {
        this.url = url;
    }
    public Response execute(GraphqlQuery query) {
        return requestSpecification
                .body(query)
                .post(url);
    }

    public Response executeGql(String fileName) {
        GraphqlQuery query = readGql(fileName);
        return execute(query);
    }

    public Response executeGql(String fileName, Map<String, Object> variables) {
        GraphqlQuery query = readGql(fileName, variables);
        return execute(query);
    }
}
