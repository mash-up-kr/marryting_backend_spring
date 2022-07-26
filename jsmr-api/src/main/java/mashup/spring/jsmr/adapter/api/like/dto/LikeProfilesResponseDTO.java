package mashup.spring.jsmr.adapter.api.like.dto;

import io.swagger.models.auth.In;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LikeProfilesResponseDTO {

    private List<String> pictures;
    private String address;
    private Integer age;
    private String career;
    private List<String> keywords;

    @Builder
    public LikeProfilesResponseDTO(List<String> pictures, String address, Integer age, String career, List<String> keywords) {
        this.pictures = pictures;
        this.address = address;
        this.age = age;
        this.career = career;
        this.keywords = keywords;
    }

    public static LikeProfilesResponseDTO from(Profile profile) {
        int nowYear = LocalDate.now().getYear();
        int age = nowYear - Integer.parseInt(profile.getBirth().substring(0, 4)) + 1;

        return LikeProfilesResponseDTO.builder()
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .address(profile.getAddress())
                .age(age)
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .build();
    }

}
