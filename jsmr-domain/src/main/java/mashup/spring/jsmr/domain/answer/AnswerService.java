package mashup.spring.jsmr.domain.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public List<Answer> saveAll(List<Answer> answers) {
        return answerRepository.saveAll(answers);
    }
}
