package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProfilesResponseDTO {

    private List<String> pictures;
    private String address;
    private String career;
    private List<String> keywords;

    @Builder
    public LikeProfilesResponseDTO(List<String> pictures, String address, String career, List<String> keywords) {
        this.pictures = pictures;
        this.address = address;
        this.career = career;
        this.keywords = keywords;
    }

    public static LikeProfilesResponseDTO from(Profile profile) {
        return LikeProfilesResponseDTO.builder()
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .address(profile.getAddress())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .build();
    }

}
