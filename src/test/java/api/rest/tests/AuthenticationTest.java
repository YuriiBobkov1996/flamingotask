package api.rest.tests;

import api.rest.helpers.ApiHelper;
import api.rest.models.AuthRequest;
import api.rest.models.AuthResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AuthenticationTest {
    private final ApiHelper apiHelper = new ApiHelper();

    @Test
    @DisplayName("Verify user can get auth token")
    public void receiveAuthTokenTest() {
        AuthRequest authRequest = new AuthRequest("admin", "password123");
        AuthResponse authResponse = apiHelper.getToken(authRequest);
        assertThat(authResponse.getToken()).isNotNull().isNotBlank();
    }
}
