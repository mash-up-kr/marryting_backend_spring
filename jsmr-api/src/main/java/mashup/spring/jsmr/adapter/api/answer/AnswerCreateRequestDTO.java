package mashup.spring.jsmr.adapter.api.answer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.question.Questionnaire;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AnswerCreateRequestDTO {

    private Long questionnaireId;
    private String answer;

    public Answer toEntity(Profile profile) {
        return Answer.builder()
                .answer(answer)
                .profile(profile)
                .questionnaireId(questionnaireId)
                .build();
    }
}
