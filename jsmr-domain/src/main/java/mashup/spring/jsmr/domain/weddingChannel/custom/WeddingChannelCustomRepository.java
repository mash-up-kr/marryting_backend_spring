package mashup.spring.jsmr.domain.weddingChannel.custom;

import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;

import java.util.List;

public interface WeddingChannelCustomRepository {

    List<WeddingChannel> findByWeddingGuestsByFetch(Profile profile, Long weddingId, List<Long> likedList);
}
