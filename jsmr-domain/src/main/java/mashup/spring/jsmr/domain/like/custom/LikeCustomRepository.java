package mashup.spring.jsmr.domain.like.custom;

import mashup.spring.jsmr.domain.like.Likes;

import java.util.List;

public interface LikeCustomRepository {
    List<Likes> findAllBySenderIdAndIsMatch(Long profileId, Boolean isMatch);

    List<Likes> findMatchingProfileWithMessage(Long profileId, Boolean isMatch);

    boolean existsBySenderAndReceiver(Long senderId, Long receiverId);
}
