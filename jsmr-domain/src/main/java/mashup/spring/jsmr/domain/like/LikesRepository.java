package mashup.spring.jsmr.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long> {
    @Query("select l from Likes l JOIN FETCH l.receiver " +
            "where l.sender.id = :profileId and l.isMatch = :isMatch")
    List<Likes> findAllBySenderIdAndIsMatch(@Param("profileId") Long profileId, @Param("isMatch") Boolean isMatch);

    Optional<Likes> findBySenderIdAndReceiverId(Long senderProfileId, Long receiverProfileId);

    @Query("select l from Likes l JOIN FETCH l.sender " +
            "where l.receiver.id = :profileId and l.isMatch = :isMatch")
    List<Likes> findMatchingProfileWithMessage(@Param("profileId") Long profileId, @Param("isMatch") Boolean isMatch);

    @Query("select count(l) > 0 from Likes l WHERE l.sender.id = :senderId and l.receiver.id = :receiverId")
    boolean existsBySenderAndReceiver(@Param("senderId") Long senderId, @Param("receiverId") Long receiverId);
}
