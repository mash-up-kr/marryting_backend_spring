package mashup.spring.jsmr.domain.wedding.custom;

import java.time.LocalDate;

public interface WeddingCustomRepository {

    void deleteWeddingsByWeddingDateIsBefore(LocalDate localDate);
}
