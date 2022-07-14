package mashup.spring.jsmr.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionnaireService {

    private final QuestionnaireRepository questionnaireRepository;

    public List<Questionnaire> getQuestionnaires(List<Long> questionnaireIds) {
        return questionnaireRepository.findAllByIdIn(questionnaireIds);
    }
}
