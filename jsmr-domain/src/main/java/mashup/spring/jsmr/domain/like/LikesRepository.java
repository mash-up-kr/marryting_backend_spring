package mashup.spring.jsmr.domain.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    List<Likes> findAllBySenderIdAndIsMatch(Long userId, Boolean isMatch);

    long countByIsMatchIsTrue();
}
