package mashup.spring.jsmr.adapter.api.questionnare.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.question.Questionnaire;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionnaireResponseDTO {

    @ApiModelProperty(value = "질문 Id", example = "1L")
    private Long questionId;

    @ApiModelProperty(value = "질문", example = "싸울 때 or 연락할 때 or 데이트할 때")
    private String question;

    @ApiModelProperty(value = "질문에 대한 대답1", example = "생각 정리하고 이야기")
    private String answer1;

    @ApiModelProperty(value = "질문에 대한 대답2", example = "그 자리에서 바로 이야기")
    private String answer2;

    @Builder
    public QuestionnaireResponseDTO(
            Long questionId,
            String question,
            String answer1,
            String answer2
    ) {
        this.questionId = questionId;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
    }

    public static QuestionnaireResponseDTO from(Questionnaire questionnaire) {
        return QuestionnaireResponseDTO.builder()
                .questionId(questionnaire.getId())
                .question(questionnaire.getQuestion())
                .answer1(questionnaire.getAnswer1())
                .answer2(questionnaire.getAnswer2())
                .build();
    }
}
