package mashup.spring.jsmr.domain.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeywordRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileKeywordRepository profileKeywordRepository;
    private final ProfileRepository profileRepository;

    public List<Profile> getDetailProfile(final Long userId, final Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);
        return profileKeywordRepository.findByProfileByFetch(userId, profile);
    }

    public Profile getProfile(final Long userId){
        return profileRepository.findByUserId(userId).orElseThrow(EntityNotFoundException::new);
    }
}
