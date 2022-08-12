package mashup.spring.jsmr.adapter.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LoginResponseDTO {

    @ApiModelProperty(value = "jwt 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiZXhwIjoxNjYxNjU0NzY5fQ.Mfeqtuw2QIiiY8OfFSyPOIT4l5Jwq-CM1PwxYndFh8I")
    private String accessToken;

    private ProfileDetailResponseDTO profile;

    @Builder
    public LoginResponseDTO(String accessToken, ProfileDetailResponseDTO profile) {
        this.accessToken = accessToken;
        this.profile = profile;
    }

    public static LoginResponseDTO from(String accessToken, ProfileDetailResponseDTO profile) {
        return LoginResponseDTO.builder()
                .accessToken(accessToken)
                .profile(profile)
                .build();
    }
}
