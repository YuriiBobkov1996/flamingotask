package api.graphql.services;

import api.graphql.clients.GraphqlClient;
import io.restassured.response.Response;

import java.util.Map;

public class MovieGraphqlService {

    private final GraphqlClient graphqlClient;

    public MovieGraphqlService(GraphqlClient graphqlClient) {
        this.graphqlClient = graphqlClient;
    }

    public Response getMoviesWithPagination(int first, int skip) {
        return graphqlClient.executeGql("moviesWithPagination.graphql", Map.of("first", first, "skip", skip));
    }

    public Response getMovieById(String id) {
        return graphqlClient.executeGql("getMovieById.graphql", Map.of("id", id));
    }

    public Response getMoviesWithPoster() {
        return graphqlClient.executeGql("moviesWithPoster.graphql");
    }
}
