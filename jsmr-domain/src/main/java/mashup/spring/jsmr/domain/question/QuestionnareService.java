package mashup.spring.jsmr.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionnareService {
    private final QuestionRepository questionRepository;

    public List<Questionnaire> getQuestionnaire() {
        return questionRepository.findAll();
    }
}
