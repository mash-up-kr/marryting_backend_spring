package mashup.spring.jsmr.adapter.api.answer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.profile.Profile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAnswerRequestDTO {

    @ApiModelProperty(value = "질문 Id", example = "1L")
    private Long questionId;

    @ApiModelProperty(value = "질문에 대답한 것", example = "생각 정리하고 이야기")
    private String answer;

    public Answer toEntity(Profile profile) {
        return Answer.builder()
                .profile(profile)
                .questionnaireId(questionId)
                .answer(answer)
                .build();
    }

}
