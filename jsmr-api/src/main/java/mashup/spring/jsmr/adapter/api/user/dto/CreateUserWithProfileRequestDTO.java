package mashup.spring.jsmr.adapter.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileRequestDTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserWithProfileRequestDTO {

    @NotBlank
    @ApiModelProperty(value = "OAuth 타입", example = "APPLE or KAKAO")
    private String oauthType;

    @NotBlank
    @ApiModelProperty(value = "ThirdParty 토큰", example = "Base64 encoded token")
    private String thirdPartyToken;

    @NotNull
    private CreateProfileRequestDTO profile;
}