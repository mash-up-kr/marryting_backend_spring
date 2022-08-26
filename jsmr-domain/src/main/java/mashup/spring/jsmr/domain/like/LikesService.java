package mashup.spring.jsmr.domain.like;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.DuplicatedException;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.wedding.Wedding;
import mashup.spring.jsmr.domain.wedding.WeddingRepository;
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
    private final WeddingRepository weddingRepository;

    @Transactional
    public Likes createLike(final Long senderId,
                            final Long receiverId,
                            final Long weddingId,
                            final String message) {

        // 중복된 좋아요 여부 체크
        if (likesRepository.existsBySenderAndReceiver(senderId, receiverId)) {
            throw new DuplicatedException();
        }

        Likes likes = likesRepository.findBySenderIdAndReceiverId(receiverId, senderId);
        if (likes == null) {
            return createMyLike(senderId, receiverId, weddingId, message, FALSE);
        }

        likes.toMatch();
        return createMyLike(senderId, receiverId, weddingId, message, TRUE);
    }

    private Likes createMyLike(final Long senderId,
                               final Long partnerProfileId,
                               final Long weddingId,
                               final String message,
                               final Boolean isMatch) {
        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(EntityNotFoundException::new);
        Profile senderProfile = profileRepository.findById(senderId)
                .orElseThrow(EntityNotFoundException::new);
        Profile partnerProfile = profileRepository.findById(partnerProfileId)
                .orElseThrow(EntityNotFoundException::new);

        Likes likes = Likes.builder()
                .sender(senderProfile)
                .receiver(partnerProfile)
                .message(message)
                .wedding(wedding)
                .sendDateTime(LocalDateTime.now())
                .isMatch(isMatch)
                .build();
        return likesRepository.save(likes);
    }
}
