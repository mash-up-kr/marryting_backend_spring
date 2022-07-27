package mashup.spring.jsmr.adapter.api.like.dto;

import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(value = "내가 좋아요한 사람들 프로필 리스트", example = "['URL1', 'URL2', 'URL3']")
    private List<String> pictures;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "나이", example = "25")
    private Integer age;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "주소", example = "['유머있는', '열정적인', '친절한']")
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
