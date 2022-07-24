package mashup.spring.jsmr.application.wedding;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.wedding.dto.WeddingParticipateListDTO;
import mashup.spring.jsmr.domain.wedding.WeddingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeddingApplicationService {

    private final WeddingService weddingService;

    public List<WeddingParticipateListDTO> getWeddingParticipateList(final Long userId) {
        return weddingService.getWeddingParticipateList(userId).stream()
                .map(WeddingParticipateListDTO::from)
                .collect(Collectors.toList());
    }
}
