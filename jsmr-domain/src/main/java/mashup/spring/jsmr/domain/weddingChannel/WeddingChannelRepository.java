package mashup.spring.jsmr.domain.weddingChannel;

import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.weddingChannel.custom.WeddingChannelCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WeddingChannelRepository extends JpaRepository<WeddingChannel, Long>, WeddingChannelCustomRepository {

    List<WeddingChannel> findAllByProfile(Profile profile);
}
