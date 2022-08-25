package mashup.spring.jsmr.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.domain.infra.RestTemplateService;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuthService {

    private final RestTemplateService restTemplateService;
    private final WebClient kaKaoWebClient;
    private final KakaoProperty kakaoProperty;

    private static final String KAKAO_AUTH_URL = "https://kapi.kakao.com/v2/user/me";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_WITH_WHITESPACE = "Bearer ";

    private static final String APPLE_SUB_KEY = "sub";

    public String verifyKakao(String accessToken) {
        try {
            var account = kaKaoWebClient
                    .post()
                    .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                    .header(HEADER_AUTHORIZATION, BEARER_WITH_WHITESPACE + accessToken)
                    .retrieve()
                    .bodyToMono(KakaoAuthResponse.class)
                    .block();

            return account.getId();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    public String verifyApple(String identityToken) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        String payload = new String(Base64Utils.decodeFromString(identityToken.split("\\.")[1]));
        return (String) parser.parseMap(payload).get(APPLE_SUB_KEY);
    }
}
