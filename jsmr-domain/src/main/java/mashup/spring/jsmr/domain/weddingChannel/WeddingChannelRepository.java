package mashup.spring.jsmr.domain.weddingChannel;

import mashup.spring.jsmr.domain.weddingChannel.custom.WeddingChannelCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeddingChannelRepository extends JpaRepository<WeddingChannel, Long>, WeddingChannelCustomRepository {
}
