package mashup.spring.jsmr.adapter.api.profile.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.dto.CreateAnswerRequestDTO;
import mashup.spring.jsmr.adapter.api.profileKeyword.dto.CreateProfileKeywordRequestDTO;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.user.User;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProfileRequestDTO {

    @ApiModelProperty(value = "이름", example = "규니")
    private String name;

    @ApiModelProperty(value = "성별(Male/FEMALE)", example = "MALE or FEMALE")
    private Gender gender;

    @ApiModelProperty(value = "직업", example = "개발자")
    private String career;

    @ApiModelProperty(value = "생년월일", example = "1990-10-03")
    private String birth;

    @ApiModelProperty(value = "주소", example = "경기도 시흥시~")
    private String address;

    @ApiModelProperty(value = "사진 URL 리스트", example = "['URL1', 'URL2', 'URL3']")
    private List<String> pictures;

    @ApiModelProperty(value = "질문에 답변한 것들")
    private List<CreateAnswerRequestDTO> answers;

    @ApiModelProperty(value = "키워드 선택한 것들")
    private List<CreateProfileKeywordRequestDTO> keywords;

    public Profile toEntity(User user) {
        return Profile.builder()
                .name(name)
                .gender(gender)
                .career(career)
                .birth(birth)
                .address(address)
                .user(user)
                .build();
    }
}

