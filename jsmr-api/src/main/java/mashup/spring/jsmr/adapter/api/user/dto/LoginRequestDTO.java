package mashup.spring.jsmr.adapter.api.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDTO {
    @NotBlank
    private String type;
    @NotBlank
    private String thirdPartyToken;
}
