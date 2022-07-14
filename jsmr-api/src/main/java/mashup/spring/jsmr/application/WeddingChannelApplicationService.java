package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannelService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeddingChannelApplicationService {

    private final WeddingChannelService weddingChannelService;

    public void getWeddingGuests(final Long userId) {
        weddingChannelService.getWeddingGuests(userId).stream()
                .map()
    }

}
