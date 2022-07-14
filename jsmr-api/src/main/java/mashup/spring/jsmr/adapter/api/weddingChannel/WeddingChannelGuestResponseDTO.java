package mashup.spring.jsmr.adapter.api.weddingChannel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeddingChannelGuestResponseDTO {

    private Long profileId;
    private String profileUrl;
    private String name;
    private Integer age;
    private String address;
    private String career;

    @Builder
    public WeddingChannelGuestResponseDTO(Long profileId,
                                          String profileUrl,
                                          String name,
                                          Integer age,
                                          String address,
                                          String career) {
        this.profileId = profileId;
        this.profileUrl = profileUrl;
        this.name = name;
        this.age = age;
        this.address = address;
        this.career = career;
    }

    public static WeddingChannelGuestResponseDTO from(WeddingChannel weddingChannel) {
        Profile profile = weddingChannel.getProfile();

        return WeddingChannelGuestResponseDTO.builder()
                .profileId(profile.getId())
                .profileUrl(profile.get)
    }
}
