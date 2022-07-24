package mashup.spring.jsmr.domain.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class QuestionnareService {
    private final QuestionRepository questionRepository;

    public List<Questionnaire> getQuestionnaire() {
        return questionRepository.findAll();
    }
}
