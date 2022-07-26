package mashup.spring.jsmr.domain.keyword;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Long> {
    List<Keyword> findAllByIdIn(List<Long> keywordIds);
}
