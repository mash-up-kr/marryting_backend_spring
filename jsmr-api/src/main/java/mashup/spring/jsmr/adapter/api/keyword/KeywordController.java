package mashup.spring.jsmr.adapter.api.keyword;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.keyword.dto.KeywordResponseDTO;
import mashup.spring.jsmr.application.KeywordApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("api/v1/keywords")
@RequiredArgsConstructor
@RestController
public class KeywordController {
    private final KeywordApplicationService keywordApplicationService;

    @ApiOperation("키워드 확인")
    @GetMapping
    public ApiResponse<List<KeywordResponseDTO>> getProfileKeyword() {

        return ApiResponse.success(HttpStatus.OK, keywordApplicationService.getKeywords());
    }
}
