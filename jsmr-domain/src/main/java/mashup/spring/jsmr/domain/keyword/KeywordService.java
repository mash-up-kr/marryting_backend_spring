package mashup.spring.jsmr.domain.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> getChoosedKeyword(List<Long> choosedKeywordId) {
        List<Keyword> choosedKeywords = keywordRepository.findAllByIdIn(choosedKeywordId);

        return choosedKeywords;
    }

    public List<Keyword> getKeyword() {
        List<Keyword> keywords = keywordRepository.findAll();

        return keywords;
    }
}
