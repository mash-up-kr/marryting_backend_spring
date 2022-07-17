package mashup.spring.jsmr.adapter.api.answer.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.question.Questionnaire;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateAnswerRequestDTO {

    private Long questionId;
    private String answer;

    public Answer toEntity(Profile profile, Questionnaire questionnaire) {
        return Answer.builder()
                .profile(profile)
                .questionnaire(questionnaire)
                .answer(answer)
                .build();
    }

}
