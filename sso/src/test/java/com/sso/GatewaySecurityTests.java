import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest
public class GatewaySecurityTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void unauthenticatedUserShouldBeRedirectedToLogin() {
        webTestClient.get().uri("/app")
                .exchange()
                .expectStatus().is3xxRedirection();
    }

    @Test
    @WithMockUser
    void authenticatedUserShouldAccessAppRoute() {
        webTestClient.get().uri("/app")
                .exchange()
                .expectStatus().isOk();
    }
}
