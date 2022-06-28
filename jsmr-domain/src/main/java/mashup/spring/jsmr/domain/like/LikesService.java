package mashup.spring.jsmr.domain.like;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;

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

}
