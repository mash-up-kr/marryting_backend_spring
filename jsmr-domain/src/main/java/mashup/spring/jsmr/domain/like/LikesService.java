package mashup.spring.jsmr.domain.like;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class LikesService {

    private final LikesRepository likesRepository;
    private final ProfileRepository profileRepository;

    /**
     * 내가 좋아요 한 사람 조회(매칭되진 않음)
     */
    public List<Profile> getMyLikesProfile(final Long userId) {
        List<Long> myLikeIds = likesRepository.findAllBySenderIdAndIsMatch(userId, FALSE).stream()
                .map(likes -> likes.getReceiver().getId())
                .collect(Collectors.toList());
        return profileRepository.findAllByUserIdIn(myLikeIds);
    }

    public List<Profile> getMyMatchingProfiles(final Long userId){
        List<Long> myMatchingIds = likesRepository.findAllBySenderIdAndIsMatch(userId, TRUE).stream()
                .map(likes -> likes.getReceiver().getId())
                .collect(Collectors.toList());
        return profileRepository.findAllByUserIdIn(myMatchingIds);
    }

    /**
     * 매칭된 상대방 메시지 조회
     */
    public String getMatchingMessage(final Long userId, final Long partnerId){
        return likesRepository.findBySenderIdAndReceiverId(userId, partnerId)
                .map(Likes::getReceiverMessage)
                .orElseGet(() -> likesRepository.findBySenderIdAndReceiverId(partnerId,userId)
                        .map(Likes::getSenderMessage)
                        .orElseThrow(EntityNotFoundException::new));
    }
    public long getTotalMatchingNumber() {
        return likesRepository.countByIsMatchIsTrue();
    }
}
