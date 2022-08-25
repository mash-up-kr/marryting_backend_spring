package mashup.spring.jsmr.domain.like;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.DuplicatedException;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final ProfileRepository profileRepository;

    @Transactional
    public Likes createLike(final Long profileId, final Long partnerProfileId, String message) {

        if (likesRepository.existsBySenderAndReceiver(profileId, partnerProfileId)){
            throw new DuplicatedException();
        }

         return likesRepository.findBySenderIdAndReceiverId(partnerProfileId, profileId)
                .map(likes -> {
                    likes.toMatch();
                    return createMyLike(profileId, partnerProfileId, message, TRUE);
                })
                 .orElse(createMyLike(profileId, partnerProfileId, message, FALSE));
    }

    private Likes createMyLike(Long profileId, Long partnerProfileId, String message, Boolean isMatch) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);
        Profile partnerProfile = profileRepository.findById(partnerProfileId)
                .orElseThrow(EntityNotFoundException::new);

        Likes likes = Likes.builder()
                .sender(profile)
                .receiver(partnerProfile)
                .message(message)
                .sendDateTime(LocalDateTime.now())
                .isMatch(isMatch)
                .build();
        return likesRepository.save(likes);
    }
}
