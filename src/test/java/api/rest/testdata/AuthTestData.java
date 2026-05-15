package api.rest.testdata;

import api.rest.models.AuthRequest;
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
}
