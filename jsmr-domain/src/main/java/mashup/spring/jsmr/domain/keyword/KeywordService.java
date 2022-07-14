package mashup.spring.jsmr.domain.keyword;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KeywordService {

    private final KeywordRepository keywordRepository;

    public List<Keyword> getUserKeyword(List<String> chooseKeyword) {
        List<Keyword> allKeywords = keywordRepository.findAll();

        List<Keyword> keywords = new ArrayList<>();
        for (Keyword allKeyword : allKeywords) {
            if (chooseKeyword.contains(allKeyword.getKeyword())) {
                keywords.add(allKeyword);
            }
        }

        return keywords;
    }
}
