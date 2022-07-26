package mashup.spring.jsmr.application.keyword;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.keyword.dto.KeywordResponseDTO;
import mashup.spring.jsmr.domain.keyword.KeywordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class KeywordApplicationService {
    private final KeywordService keywordService;

    public List<KeywordResponseDTO> getKeywords() {
        return keywordService.getKeyword().stream()
                .map(KeywordResponseDTO::from)
                .collect(Collectors.toList());
    }
}
