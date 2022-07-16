package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.weddingChannel.WeddingChannelGuestResponseDTO;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeddingChannelApplicationService {

    private final WeddingChannelService weddingChannelService;

    public List<WeddingChannelGuestResponseDTO> getWeddingGuests(final Long userId) {
        return weddingChannelService.getWeddingGuests(userId).stream()
                .map(WeddingChannelGuestResponseDTO::from)
                .collect(Collectors.toList());
    }

}
