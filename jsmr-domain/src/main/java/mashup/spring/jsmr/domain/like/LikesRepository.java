package mashup.spring.jsmr.domain.like;

import mashup.spring.jsmr.domain.like.custom.LikeCustomRepository;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikesRepository extends JpaRepository<Likes, Long>, LikeCustomRepository {

    Likes findBySenderIdAndReceiverId(Long senderProfileId, Long receiverProfileId);

    List<Likes> findAllBySender(Profile sender);
}
