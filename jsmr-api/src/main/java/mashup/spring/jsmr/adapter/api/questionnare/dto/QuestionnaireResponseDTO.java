package mashup.spring.jsmr.adapter.api.questionnare.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.question.Questionnaire;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuestionnaireResponseDTO {

    private Long questionId;
    private String question;
    private String answer1;
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
