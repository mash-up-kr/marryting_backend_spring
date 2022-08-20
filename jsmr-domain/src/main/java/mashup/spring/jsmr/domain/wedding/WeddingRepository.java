package mashup.spring.jsmr.domain.wedding;

import mashup.spring.jsmr.domain.wedding.custom.WeddingCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeddingRepository extends JpaRepository<Wedding, Long>, WeddingCustomRepository {

    boolean existsByWeddingCode(String weddingCode);

    Optional<Wedding> findByWeddingCode(String weddingCode);
}
