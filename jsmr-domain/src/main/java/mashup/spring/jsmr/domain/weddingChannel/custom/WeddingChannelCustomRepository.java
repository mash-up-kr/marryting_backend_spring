package mashup.spring.jsmr.domain.weddingChannel.custom;

import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

import java.util.List;

public interface WeddingChannelCustomRepository {

    List<WeddingChannel> findByWeddingGuestsByFetch(Gender gender);
}
