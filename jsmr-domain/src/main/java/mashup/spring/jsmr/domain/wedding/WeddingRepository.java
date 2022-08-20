package mashup.spring.jsmr.domain.wedding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Optional;

public interface WeddingRepository extends JpaRepository<Wedding, Long> {

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("delete from Wedding w where w.weddingDate < :localDate")
    void deleteWeddingsByWeddingDateIsBefore(LocalDate localDate);

    boolean existsByWeddingCode(String weddingCode);

    Optional<Wedding> findByWeddingCode(String weddingCode);
}
