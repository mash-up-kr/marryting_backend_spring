package mashup.spring.jsmr.domain.answer;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.question.Questionnaire;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void saveAll(List<Questionnaire> questionnaires, List<String> answers, Profile profile) {
        List<Answer> answerList = new ArrayList<>();
        for (int i = 0; i < questionnaires.size(); i++) {
            Answer answer = Answer.builder()
                    .questionnaire(questionnaires.get(i))
                    .answer(answers.get(i))
                    .profile(profile)
                    .build();

            answerList.add(answer);
        }
        answerRepository.saveAll(answerList);
    }
}
