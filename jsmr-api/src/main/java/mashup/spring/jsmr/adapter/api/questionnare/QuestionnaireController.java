package mashup.spring.jsmr.adapter.api.questionnare;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.questionnare.dto.QuestionnaireResponseDTO;
import mashup.spring.jsmr.application.questionnare.QuestionnareApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/questionnaire")
@RequiredArgsConstructor
@RestController
public class QuestionnaireController {
    private final QuestionnareApplicationService questionnareApplicationService;

    @ApiOperation("질문지 확인")
    @GetMapping
    public ApiResponse<List<QuestionnaireResponseDTO>> getProfileQuestionnaire() {
        return ApiResponse.success(HttpStatus.OK, questionnareApplicationService.getQuestionnaire());
    }
}
