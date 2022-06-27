package mashup.spring.jsmr.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByUserIdIn(List<Long> userIds);
}
