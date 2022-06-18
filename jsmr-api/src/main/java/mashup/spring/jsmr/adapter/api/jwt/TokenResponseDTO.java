package mashup.spring.jsmr.adapter.api.jwt;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponseDTO {

    private String accessToken;
    private Date accessTokenExpiredAt;

    @Builder
    public TokenResponseDTO(String accessToken, Date accessTokenExpiredAt) {
        this.accessToken = accessToken;
        this.accessTokenExpiredAt = accessTokenExpiredAt;
    }
}
