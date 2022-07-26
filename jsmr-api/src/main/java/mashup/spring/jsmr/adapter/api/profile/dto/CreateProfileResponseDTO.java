package mashup.spring.jsmr.adapter.api.profile.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProfileResponseDTO {
    private Long profileId;

    @Builder
    public CreateProfileResponseDTO(Long profileId) {
        this.profileId = profileId;
    }

    public static CreateProfileResponseDTO from(Profile profile) {
        return CreateProfileResponseDTO.builder()
                .profileId(profile.getId())
                .build();
    }
}
