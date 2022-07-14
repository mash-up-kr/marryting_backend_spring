package mashup.spring.jsmr.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByUserIdIn(List<Long> userIds);
    Optional<Profile> findAllByUserId(Long userId);
}
