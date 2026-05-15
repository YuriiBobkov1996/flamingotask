package api.rest.testdata;

import api.rest.models.AuthRequest;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static config.TestConfig.config;

public class AuthTestData {

    private AuthTestData() {
    }
    public static AuthRequest validAuthRequest() {
        return AuthRequest.builder()
                .username(config().apiUsername())
                .password(config().apiPassword())
                .build();
    }

    public static Stream<Arguments> invalidAuthData() {
        return Stream.of(
                Arguments.of("wrongUser", config().apiPassword()),
                Arguments.of(config().apiUsername(), "wrongPassword"),
                Arguments.of("wrongUser", "wrongPassword"),
                Arguments.of("", "")
        );
    }
}
