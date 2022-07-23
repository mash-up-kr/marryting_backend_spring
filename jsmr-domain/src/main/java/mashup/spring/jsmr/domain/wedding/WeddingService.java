package mashup.spring.jsmr.domain.wedding;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class WeddingService {

    private final WeddingRepository weddingRepository;

    @Transactional
    public void deleteExpiredWeddingsAsOfToday(){  // wedding 은 weddingDate 일자가 지나면 제거된다.
        weddingRepository.deleteWeddingsByWeddingDateIsBefore(LocalDate.now());
    }
}
