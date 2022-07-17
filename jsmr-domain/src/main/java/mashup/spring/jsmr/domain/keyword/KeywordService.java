package mashup.spring.jsmr.domain.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordService {
    private final KeywordRepository keywordRepository;

    public List<Keyword> getChoosedKeyword(List<String> choosedKeyword) {
        List<Keyword> allKeywords = keywordRepository.findAll();

        List<Keyword> keywords = new ArrayList<>();
        for (Keyword keyword : allKeywords) {
            if (choosedKeyword.contains(keyword.getKeyword())) {
                keywords.add(keyword);
            }
        }

        return keywords;
    }

    public List<Keyword> getKeyword() {
        List<Keyword> keywords = keywordRepository.findAll();

        return keywords;
    }
}
