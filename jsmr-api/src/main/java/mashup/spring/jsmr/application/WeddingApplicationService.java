package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.wedding.WeddingService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WeddingApplicationService {

    private final WeddingService weddingService;

    public void deleteExpiredWeddings(){
        weddingService.deleteExpiredWeddingsAsOfToday();
    }
}
