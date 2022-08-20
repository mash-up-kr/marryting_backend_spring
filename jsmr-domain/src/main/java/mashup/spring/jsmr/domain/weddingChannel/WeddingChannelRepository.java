package mashup.spring.jsmr.domain.weddingChannel;

import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.weddingChannel.custom.WeddingChannelCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WeddingChannelRepository extends JpaRepository<WeddingChannel, Long>, WeddingChannelCustomRepository {

    List<WeddingChannel> findAllByProfile(Profile profile);

    @Query("select w from WeddingChannel w " +
            "join fetch w.profile " +
            "where w.profile.gender <> :#{#profile.gender} " +
            "and w.profile.id <> :#{#profile.id} " +
            "and w.profile.id not in :likedList")
    List<WeddingChannel> findByProfile(@Param("profile") Profile profile, @Param("likedList") List<Long> likedList);

    boolean existsWeddingChannelByProfile(Profile profile);
}
