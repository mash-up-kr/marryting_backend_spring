package mashup.spring.jsmr.domain.profileKeyword;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileKeywordService {

    private final ProfileKeywordRepository profileKeywordRepository;

    @Transactional
    public List<ProfileKeyword> saveAll(List<Keyword> choosedKeywords, Profile profile) {
        List<ProfileKeyword> profileKeywords = new ArrayList<>();
        for (Keyword keyword : choosedKeywords) {
            ProfileKeyword profileKeyword = ProfileKeyword.builder()
                    .profile(profile)
                    .keyword(keyword)
                    .build();
            profileKeywords.add(profileKeyword);
        }

        return profileKeywordRepository.saveAll(profileKeywords);
    }
}
