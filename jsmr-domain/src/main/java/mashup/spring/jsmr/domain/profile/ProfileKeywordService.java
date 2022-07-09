package mashup.spring.jsmr.domain.profile;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileKeywordService {

    private final ProfileKeywordRepository profileKeywordRepository;

    @Transactional
    public void saveAll(List<ProfileKeyword> profileKeywords) {
        profileKeywordRepository.saveAll(profileKeywords);
    }
}
