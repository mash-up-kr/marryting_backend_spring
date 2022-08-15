package mashup.spring.jsmr.domain.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.domain.infra.RestTemplateService;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuthService {

    private final RestTemplateService restTemplateService;

    private static final String KAKAO_AUTH_URL = "https://kapi.kakao.com/v1/user/access_token_info";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_WITH_WHITESPACE = "Bearer ";

    private static final String APPLE_SUB_KEY = "sub";

    public KakaoAuthResponse verifyKakao(String accessToken) {
        Map<String, List<String>> headers = new HashMap<>();
        headers.put(HEADER_AUTHORIZATION, List.of(BEARER_WITH_WHITESPACE + accessToken));
        MultiValueMap<String, String> header = CollectionUtils.toMultiValueMap(headers);
        ResponseEntity<KakaoAuthResponse> response = restTemplateService.get(
                KAKAO_AUTH_URL,
                new HttpHeaders(header),
                KakaoAuthResponse.class
        );
        return response.getBody();
    }

    public String verifyApple(String identityToken) {
        JsonParser parser = JsonParserFactory.getJsonParser();
        String payload = new String(Base64Utils.decodeFromString(identityToken.split("\\.")[1]));
        return (String) parser.parseMap(payload).get(APPLE_SUB_KEY);
    }
}
