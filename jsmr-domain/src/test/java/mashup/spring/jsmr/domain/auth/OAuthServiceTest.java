package mashup.spring.jsmr.domain.auth;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class OAuthServiceTest {

    @InjectMocks
    OAuthService authService;

    @Test
    void verifyAppleId() {
        String appleId = authService.verifyApple("000871.31eedc54c602460da26f4765dd27e985.1412");
        System.out.println(appleId);
    }

}