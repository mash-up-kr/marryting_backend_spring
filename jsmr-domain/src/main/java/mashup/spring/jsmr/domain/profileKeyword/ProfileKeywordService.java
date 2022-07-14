package mashup.spring.jsmr.domain.profileKeyword;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileKeywordService {

    private final ProfileKeywordRepository profileKeywordRepository;

    public List<ProfileKeyword> saveAll(List<Keyword> keywords, Profile profile) {
        List<ProfileKeyword> profileKeywords = new ArrayList<>();
        for (Keyword keyword : keywords) {
            profileKeywords.add(
                    ProfileKeyword.builder()
                            .profile(profile)
                            .keyword(keyword)
                            .build()
            );
        }

        return profileKeywordRepository.saveAll(profileKeywords);
    }
}
