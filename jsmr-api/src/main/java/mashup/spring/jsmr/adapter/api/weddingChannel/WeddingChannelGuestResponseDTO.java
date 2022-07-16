package mashup.spring.jsmr.adapter.api.weddingChannel;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WeddingChannelGuestResponseDTO {

    private Long profileId;
    private String name;
    private Integer age;
    private String address;
    private String career;

    private String profileUrl;
    private List<String> keywords;

    @Builder
    public WeddingChannelGuestResponseDTO(Long profileId,
                                          String name,
                                          Integer age,
                                          String address,
                                          String career,
                                          String profileUrl,
                                          List<String> keywords) {
        this.profileId = profileId;
        this.name = name;
        this.age = age;
        this.address = address;
        this.career = career;
        this.profileUrl = profileUrl;
        this.keywords = keywords;
    }

    public static WeddingChannelGuestResponseDTO from(WeddingChannel weddingChannel) {
        Profile profile = weddingChannel.getProfile();

        return WeddingChannelGuestResponseDTO.builder()
                .profileId(profile.getId())
                .name(profile.getName())
                .address(profile.getAddress())
                .age(profile.getAge())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(profileKeyword -> profileKeyword.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .build();
    }
}
