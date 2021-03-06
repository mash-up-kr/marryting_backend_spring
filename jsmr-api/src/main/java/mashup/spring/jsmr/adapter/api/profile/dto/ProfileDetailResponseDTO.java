package mashup.spring.jsmr.adapter.api.profile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileDetailResponseDTO {

    @ApiModelProperty(value = "프로필 Id", example = "1L")
    private Long profileId;

    @ApiModelProperty(value = "프로필 이름", example = "규니")
    private String profileName;

    @ApiModelProperty(value = "나이", example = "25")
    private Integer age;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "키워드 리스트", example = "['keyword1', 'keyword3', 'keyword3']")
    private List<String> keywords;

    @ApiModelProperty(value = "답변 리스트", example = "['싸울 땐 생각을 정리하고 이야기', '연락은 자주 할수록 좋아요']")
    private List<String> answers;

    @ApiModelProperty(value = "사진 리스트", example = "['URL1', 'URL2']")
    private List<String> pictures;

    @Builder
    public ProfileDetailResponseDTO(
            Long profileId,
            String profileName,
            Integer age,
            String address,
            String career,
            List<String> keywords,
            List<String> answers,
            List<String> pictures
    ) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.age = age;
        this.address = address;
        this.career = career;
        this.keywords = keywords;
        this.answers = answers;
        this.pictures = pictures;
    }

    public static ProfileDetailResponseDTO from(Profile profile) {
        int nowYear = LocalDate.now().getYear();
        int age = nowYear - Integer.parseInt(profile.getBirth().substring(0, 4)) + 1;

        return ProfileDetailResponseDTO.builder()
                .profileId(profile.getId())
                .profileName(profile.getName())
                .age(age)
                .address(profile.getAddress())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .answers(profile.getAnswers().stream()
                        .map(Answer::getAnswer)
                        .collect(Collectors.toList()))
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .build();
    }
}
