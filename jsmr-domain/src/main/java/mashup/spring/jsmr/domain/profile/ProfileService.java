package mashup.spring.jsmr.domain.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.user.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public Profile getDetailProfile(final Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }

    public Profile getProfile(final Long userId){
        return profileRepository.findByUserId(userId).orElseThrow(EntityNotFoundException::new);
    }

    public boolean existProfile(final User user){
        return profileRepository.existsProfileByUser(user);
    }
}
