package mashup.spring.jsmr.adapter.api.profile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.dto.AnswerResponseDTO;
import mashup.spring.jsmr.adapter.api.keyword.dto.KeywordResponseDTO;
import mashup.spring.jsmr.adapter.util.AgeUtil;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileDetailResponseDTO {

    @ApiModelProperty(value = "프로필 Id", example = "1")
    private Long profileId;

    @ApiModelProperty(value = "프로필 이름", example = "규니")
    private String profileName;

    @ApiModelProperty(value = "나이", example = "25")
    private Integer age;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "성별", example = "MALE")
    private String gender;

    @ApiModelProperty(value = "키워드 리스트", example = "키워드 리스트")
    private List<KeywordResponseDTO> keywords;

    @ApiModelProperty(value = "답변 리스트", example = "답변 리스트")
    private List<AnswerResponseDTO> answers;

    @ApiModelProperty(value = "사진 리스트", example = "['URL1', 'URL2']")
    private List<String> pictures;

    @Builder
    public ProfileDetailResponseDTO(
            Long profileId,
            String profileName,
            Integer age,
            String address,
            String career,
            String gender,
            List<KeywordResponseDTO> keywords,
            List<AnswerResponseDTO> answers,
            List<String> pictures
    ) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.age = age;
        this.address = address;
        this.career = career;
        this.gender = gender;
        this.keywords = keywords;
        this.answers = answers;
        this.pictures = pictures;
    }

    public static ProfileDetailResponseDTO from(Profile profile) {
        return ProfileDetailResponseDTO.builder()
                .profileId(profile.getId())
                .profileName(profile.getName())
                .age(AgeUtil.calculateUserAge(profile.getBirth()))
                .address(profile.getAddress())
                .gender(profile.getGender().name())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> KeywordResponseDTO.from(p.getKeyword()))
                        .collect(Collectors.toList()))
                .answers(profile.getAnswers().stream()
                        .map(AnswerResponseDTO::from)
                        .collect(Collectors.toList()))
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .build();
    }
}
