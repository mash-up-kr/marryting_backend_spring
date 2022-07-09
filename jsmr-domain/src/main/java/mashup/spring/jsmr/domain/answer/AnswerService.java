package mashup.spring.jsmr.domain.answer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    public void saveAll(List<Answer> answers) {
        answerRepository.saveAll(answers);
    }
}
