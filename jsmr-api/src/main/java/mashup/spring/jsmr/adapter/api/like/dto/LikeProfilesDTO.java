package mashup.spring.jsmr.adapter.api.like.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.profile.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProfilesDTO {

    private List<String> picture;
    private String address;
    private Integer age;
    private String career;
    private List<String> keywords;

    @Builder
    public LikeProfilesDTO(List<String> picture, String address, Integer age, String career, List<String> keywords) {
        this.picture = picture;
        this.address = address;
        this.age = age;
        this.career = career;
        this.keywords = keywords;
    }

    public static LikeProfilesDTO from(Profile profile) {
        return LikeProfilesDTO.builder()
                .picture(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .address(profile.getAddress())
                .age(profile.getAge())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .build();
    }

}
