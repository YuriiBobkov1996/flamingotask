package api.graphql.helpers;

import api.graphql.models.GraphqlQuery;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;

public class GraphqlUtils {

    private GraphqlUtils() {
    }

    public static GraphqlQuery gql(String query) {
        return new GraphqlQuery(query, null);
    }

    public static GraphqlQuery gql(String query,
                                   Map<String, Object> variables) {

        return new GraphqlQuery(query, variables);
    }

    public static GraphqlQuery readGql(String fileName) {
        return gql(readFile(fileName));
    }

    public static GraphqlQuery readGql(String fileName, Map<String, Object> variables) {
        return gql(readFile(fileName), variables);
    }

    private static String readFile(String fileName) {

        URL url = GraphqlUtils.class
                .getClassLoader()
                .getResource("graphql/" + fileName);

        File file = new File(
                Objects.requireNonNull(url).getFile()
        );

        try {
            return new String(
                    Files.readAllBytes(file.toPath())
            );

        } catch (IOException e) {
            throw new RuntimeException(
                    "Unable to read GraphQL file: " + fileName,
                    e
            );
        }
    }
}
