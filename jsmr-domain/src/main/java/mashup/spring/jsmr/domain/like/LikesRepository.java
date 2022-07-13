package mashup.spring.jsmr.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("select l from Likes l JOIN FETCH l.receiver " +
            "where l.sender = :profileId and l.isMatch = :isMatch")
    List<Likes> findAllBySenderIdAndIsMatch(Long profileId, Boolean isMatch);

    Optional<Likes> findBySenderIdAndReceiverId(Long senderProfileId, Long receiverProfileId);

    @Query("select l from Likes l JOIN FETCH l.sender " +
            "where l.receiver = :profileId and l.isMatch = :isMatch")
    List<Likes> findMatchingProfileWithMessage(Long profileId, Boolean isMatch);
}
