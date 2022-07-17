package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.questionnare.dto.QuestionnaireResponseDTO;
import mashup.spring.jsmr.domain.question.QuestionRepository;
import mashup.spring.jsmr.domain.question.QuestionnareService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuestionnareApplicationService {
    private final QuestionnareService questionnareService;

    public List<QuestionnaireResponseDTO> getQuestionnaire() {
        return questionnareService.getQuestionnaire().stream()
                .map(QuestionnaireResponseDTO::from)
                .collect(Collectors.toList());
    }
}
