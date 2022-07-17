package mashup.spring.jsmr.domain.profile;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

    List<Profile> findAllByUserIdIn(List<Long> userIds);

    @Query("select p from Profile p join fetch p.user where p.user = :userId")
    Optional<Profile> findByUserId(Long userId);
}
