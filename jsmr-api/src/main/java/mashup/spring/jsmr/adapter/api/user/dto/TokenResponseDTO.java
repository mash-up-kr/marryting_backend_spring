package mashup.spring.jsmr.adapter.api.user.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponseDTO {
    private String accessToken;

    @Builder
    public TokenResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public static TokenResponseDTO from(String accessToken) {
        return TokenResponseDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
