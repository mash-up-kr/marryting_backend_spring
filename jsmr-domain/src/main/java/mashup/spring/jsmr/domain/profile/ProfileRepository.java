package mashup.spring.jsmr.domain.profile;

import mashup.spring.jsmr.domain.profile.custom.ProfileCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileCustomRepository {
    
    Optional<Profile> findAllByUserId(Long userId);

    @Query("select p from Profile p join fetch p.user where p.user = :userId")
    Optional<Profile> findByUserId(Long userId);
}
