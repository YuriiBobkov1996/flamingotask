package api.graphql.tests;

import api.graphql.clients.GraphqlClient;
import api.graphql.services.MovieGraphqlService;
import config.TestConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;
import java.util.Random;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("graphql")
public class GQLPositiveTest {

    private final MovieGraphqlService movieGraphqlService = new MovieGraphqlService(new GraphqlClient(TestConfig.config().graphqlUrl()));

    @Test
    @DisplayName("Should return movies list with pagination")
    public void returnMoviesWithPaginationTest() {
        Response response = movieGraphqlService.getMoviesWithPagination(3, 0);
        List<Map<String, Object>> movies = response.jsonPath().getList("data.movies");
        Object errors = response.jsonPath().get("errors");
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(movies).isNotNull().hasSizeLessThanOrEqualTo(3);
        assertThat(movies).allSatisfy(movie -> {
            assertThat(movie.get("id")).isNotNull();
            assertThat(movie.get("title")).isNotNull();
        });
        assertThat(errors).isNull();
    }

    @Test
    @DisplayName("Should retrieve a movie by ID using GraphQL variables")
    public void retrieveMovieByIdTestUsingGraphqlVariables() {
        List<String> movieIds = movieGraphqlService.getMoviesWithPagination(10, 0).jsonPath().getList("data.movies.id");
        String movieId = movieIds.get(new Random().nextInt(movieIds.size()));
        Response response = movieGraphqlService.getMovieById(movieId);
        Map<String, Object> movie = response.jsonPath().getMap("data.movie");
        Object errors = response.jsonPath().get("errors");
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(movie).isNotNull();
        assertThat(movie.get("id")).isEqualTo(movieId);
        assertThat(movie.get("title")).isNotNull();
        assertThat(errors).isNull();

    }

    @Test
    @DisplayName("Should return movies with nested poster fields using a fragment")
    public void shouldReturnMoviesWithNestedPosterUsingFragment() {
        Response response = movieGraphqlService.getMoviesWithPoster();
        List<Map<String, Object>> movies = response.jsonPath().getList("data.movies");
        Object errors = response.jsonPath().get("errors");
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(movies).isNotEmpty();
        assertThat(movies).allSatisfy(movie -> {
            assertThat(movie.get("id")).isNotNull();
            assertThat(movie.get("title")).isNotNull();
            Object poster = movie.get("moviePoster");
            if (poster != null) {
                Map<String, Object> moviePoster = (Map<String, Object>) poster;
                assertThat(moviePoster.get("url")).isNotNull();
                assertThat(moviePoster.get("width")).isNotNull();
                assertThat(moviePoster.get("height")).isNotNull();
            }
        });
        assertThat(errors).isNull();
    }
}
