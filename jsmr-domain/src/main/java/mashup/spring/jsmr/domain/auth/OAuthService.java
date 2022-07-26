package mashup.spring.jsmr.domain.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.domain.exception.BusinessException;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.exception.ExceptionCode;
import mashup.spring.jsmr.domain.infra.RestTemplateService;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Service
@Slf4j
public class OAuthService {

    private final RestTemplateService restTemplateService;

    private static final String KAKAO_AUTH_URL = "https://kauth.kakao.com//v1/user/access_token_info";
    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String BEARER_WITH_WHITESPACE = "Bearer ";

    private static final String APPLE_GET_KEY_URL = "https://appleid.apple.com/auth/keys";

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
        Map<String, Object> header = parser.parseMap(
                new String(Base64Utils.decodeFromString(identityToken.split("\\.")[0]))
        );
        String kid = String.valueOf(header.get("kid"));
        String alg = String.valueOf(header.get("alg"));
        List<AppleKey> keys = Objects.requireNonNull(
                restTemplateService.get(
                        APPLE_GET_KEY_URL,
                        new HttpHeaders(),
                        AppleKeyResponse.class
                ).getBody()
        ).getKeys();
        AppleKey correctKey = keys.stream().filter(appleKey ->
                Objects.equals(appleKey.getKid(), kid) && Objects.equals(appleKey.getAlg(), alg)
        ).findFirst().orElseThrow(EntityNotFoundException::new);
        PublicKey publicKey = generatePublicKey(correctKey);
        Claims userInfo = Jwts.parser().setSigningKey(publicKey).parseClaimsJws(identityToken).getBody();
        return userInfo.get("sub", String.class);
    }

    private PublicKey generatePublicKey(AppleKey key) {
        byte[] nBytes = Base64Utils.decodeFromString(key.getN());
        byte[] eBytes = Base64Utils.decodeFromString(key.getE());

        BigInteger n = new BigInteger(1, nBytes);
        BigInteger e = new BigInteger(1, eBytes);

        try {
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePublic(publicKeySpec);
        } catch (Exception exception) {
            throw new BusinessException(ExceptionCode.FAIL_AUTHORIZATION);
        }
    }
}
