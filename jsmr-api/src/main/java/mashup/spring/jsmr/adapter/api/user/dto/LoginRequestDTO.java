package mashup.spring.jsmr.adapter.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginRequestDTO {

    @NotBlank
    @ApiModelProperty(value = "로그인타입", example = "APPLE or KAKAO")
    private String type;

    @NotBlank
    @ApiModelProperty(value = "ThirdParty 토큰", example = "Base64 encoded token")
    private String thirdPartyToken;
}
