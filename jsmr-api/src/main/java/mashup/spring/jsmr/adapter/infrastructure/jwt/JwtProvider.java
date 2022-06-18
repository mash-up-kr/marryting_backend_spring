package mashup.spring.jsmr.adapter.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.jwt.TokenResponseDTO;
import org.springframework.stereotype.Component;

import javax.xml.bind.DatatypeConverter;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProvider {

    private final JwtProperty jwtProperty;

    private String createToken(final long payload, final String secretKey, final Long tokenValidTime) {
        return Jwts.builder()
                .setSubject(String.valueOf(payload))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .compact();
    }

    private String createAccessToken(final long payload) {
        return createToken(payload, jwtProperty.getAccessTokenSecretKey(), jwtProperty.getAccessTokenValidTime());
    }

    public TokenResponseDTO createTokenResponse(long userId) {
        var accessToken = createAccessToken(userId);

        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(getTokenExpiredTime(accessToken, jwtProperty.getAccessTokenSecretKey()))
                .build();
    }

    private Date getTokenExpiredTime(final String accessToken, final String secretKey) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secretKey))
                .parseClaimsJws(accessToken)
                .getBody()
                .getExpiration();
    }

    public Long getAccessTokenPayload(String accessToken) {
        var claims = Jwts.parser()
                .setSigningKey(jwtProperty.getAccessTokenSecretKey())
                .parseClaimsJws(accessToken)
                .getBody();

        return Long.parseLong(claims.getSubject());
    }

    public boolean validateToken(String accessToken) {
        try {
            var claims = Jwts.parser()
                    .setSigningKey(jwtProperty.getAccessTokenSecretKey())
                    .parseClaimsJws(accessToken);

            return !claims.getBody().getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

}
