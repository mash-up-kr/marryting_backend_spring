package mashup.spring.jsmr.adapter.api.like.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.dto.AnswerResponseDTO;
import mashup.spring.jsmr.adapter.api.keyword.dto.KeywordResponseDTO;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeyword;
import mashup.spring.jsmr.domain.user.Gender;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchingProfileResponseDTO {

    @ApiModelProperty(value = "프로필 id", example = "1")
    private Long profileId;

    @ApiModelProperty(value = "이름", example = "김흥부")
    private String name;

    @ApiModelProperty(value = "성별(Male/FEMALE)", example = "MALE or FEMALE")
    private Gender gender;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "나이", example = "25")
    private Integer age;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "질문에 답변한 것들")
    private List<AnswerResponseDTO> answers;

    @ApiModelProperty(value = "키워드 선택한 것들")
    private List<KeywordResponseDTO> keywords;

    @ApiModelProperty(value = "내가 좋아요한 사람들 프로필 리스트", example = "['URL1', 'URL2', 'URL3']")
    private List<String> pictures;

    @ApiModelProperty(value = "좋아요 보낼 때 보낸 메세지", example = "너가 좋아~")
    private String message;

    @ApiModelProperty(value = "좋아요 여부", example = "TRUE or FALSE")
    private Boolean isLike;

    @Builder
    public MatchingProfileResponseDTO(
            Long profileId,
            String name,
            Gender gender,
            String address,
            Integer age,
            String career,
            List<AnswerResponseDTO> answers,
            List<KeywordResponseDTO> keywords,
            List<String> pictures,
            Boolean isLike,
            String message
    ) {
        this.profileId = profileId;
        this.name = name;
        this.gender = gender;
        this.address = address;
        this.age = age;
        this.career = career;
        this.answers = answers;
        this.keywords = keywords;
        this.pictures = pictures;
        this.isLike = isLike;
        this.message = message;
    }

    public static MatchingProfileResponseDTO from(Profile profile, String message) {
        int nowYear = LocalDate.now().getYear();
        int age = nowYear - Integer.parseInt(profile.getBirth().substring(0, 4)) + 1;

        return MatchingProfileResponseDTO.builder()
                .profileId(profile.getId())
                .name(profile.getName())
                .gender(profile.getGender())
                .address(profile.getAddress())
                .age(age)
                .career(profile.getCareer())
                .answers(profile.getAnswers().stream()
                        .map(AnswerResponseDTO::from)
                        .collect(Collectors.toList()))
                .keywords(profile.getProfileKeywords().stream()
                        .map(ProfileKeyword::getKeyword)
                        .map(KeywordResponseDTO::from)
                        .collect(Collectors.toList()))
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .isLike(Boolean.TRUE)
                .message(message)
                .build();
    }
}
