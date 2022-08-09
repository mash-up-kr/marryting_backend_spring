package mashup.spring.jsmr.adapter.api.answer.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerResponseDTO {

    @ApiModelProperty(value = "질문 Id", example = "1L")
    private Long questionId;

    @ApiModelProperty(value = "질문에 대답한 것", example = "생각 정리하고 이야기")
    private String answer;

    @Builder
    public AnswerResponseDTO(Long questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public static AnswerResponseDTO from(Answer answer){
        return AnswerResponseDTO.builder()
                .questionId(answer.getQuestionnaireId())
                .answer(answer.getAnswer())
                .build();
    }
}
