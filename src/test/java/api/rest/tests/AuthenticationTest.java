package api.rest.tests;

import api.rest.helpers.ApiHelper;
import api.rest.models.AuthRequest;
import api.rest.models.AuthResponse;
import api.rest.testdata.AuthTestData;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.apache.http.HttpStatus.SC_OK;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

 @Tag("rest")
 public class AuthenticationTest {
    private final ApiHelper apiHelper = new ApiHelper();

    @Test
    @DisplayName("Verify user can get auth token")
    public void receiveAuthTokenTest() {
        AuthResponse authResponse = apiHelper.getToken(AuthTestData.validAuthRequest());
        assertThat(authResponse.getToken()).isNotNull().isNotBlank();
    }

    @ParameterizedTest(name = "Invalid credentials: username={0}, password={1}")
    @MethodSource("api.rest.testdata.AuthTestData#invalidAuthData")
    @DisplayName("Verify user cannot get auth token with invalid credentials")
    public void receiveAuthTokenWithInvalidCredentialsTest(String username, String password) {
        AuthRequest authRequest = AuthRequest.builder().username(username).password(password).build();
        Response response = apiHelper.getTokenResponse(authRequest);
        assertThat(response.statusCode()).isEqualTo(SC_OK);
        assertThat(response.jsonPath().getString("reason")).isEqualTo("Bad credentials");
    }
}
