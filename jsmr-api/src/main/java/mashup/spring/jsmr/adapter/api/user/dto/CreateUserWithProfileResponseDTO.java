package mashup.spring.jsmr.adapter.api.user.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateUserWithProfileResponseDTO {

    @ApiModelProperty(value = "jwt 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1IiwiZXhwIjoxNjYxNjU0NzY5fQ.Mfeqtuw2QIiiY8OfFSyPOIT4l5Jwq-CM1PwxYndFh8I")
    private String accessToken;

    @ApiModelProperty(value = "프로필 Id", example = "1L")
    private Long profileId;

    @Builder
    public CreateUserWithProfileResponseDTO(String accessToken, Long profileId) {
        this.accessToken = accessToken;
        this.profileId = profileId;
    }

    public static CreateUserWithProfileResponseDTO from(String accessToken, Long profileId) {
        return CreateUserWithProfileResponseDTO.builder()
                .accessToken(accessToken)
                .profileId(profileId)
                .build();
    }
}
