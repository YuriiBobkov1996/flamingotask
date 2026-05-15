package api.graphql.tests;

import api.graphql.clients.GraphqlClient;
import api.graphql.services.MovieGraphqlService;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static org.apache.http.HttpStatus.*;
import static org.assertj.core.api.Assertions.assertThat;


@Tag("api graphql")
public class GQLNegativeTest {

    private final MovieGraphqlService movieGraphqlService = new MovieGraphqlService(new GraphqlClient(TestConfig.config().graphqlUrl()));

    @Test
    public void returnNullForInvalidMovieIdTest() {
        String invalidId = "invalid-id";
        Response response = movieGraphqlService.getMovieById(invalidId);
        Map<String, Object> movie = response.jsonPath().getMap("data.movie");
        Object errors = response.jsonPath().get("errors");
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(movie).isNull();
        assertThat(errors).isNull();
    }

    @Test
    public void returnSyntaxErrorForMalformedQueryTest() {
        Response response = movieGraphqlService.getMalformedQuery();
        List<Map<String, Object>> errors = response.jsonPath().getList("errors");
        Object data = response.jsonPath().get("data");
        assertThat(response.statusCode()).isEqualTo(SC_BAD_REQUEST);
        assertThat(errors).isNotEmpty();
        assertThat(errors.getFirst().get("message").toString()).contains("Invalid query").contains("ParseError").contains("Unexpected");
        assertThat(data).isNull();
    }

    @Test
    public void returnValidationErrorForNonExistentFieldTest() {
        Response response = movieGraphqlService.getMoviesWithNonExistentField();
        List<Map<String, Object>> errors = response.jsonPath().getList("errors");
        Object data = response.jsonPath().get("data");
        assertThat(response.statusCode()).isEqualTo(SC_BAD_REQUEST);
        assertThat(data).isNull();
        assertThat(errors).isNotEmpty();
        assertThat(errors.getFirst().get("message").toString()).contains("fakeField").contains("is not defined").contains("Movie");
    }
}
