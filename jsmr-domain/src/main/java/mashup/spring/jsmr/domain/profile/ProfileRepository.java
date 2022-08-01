package mashup.spring.jsmr.domain.profile;

import mashup.spring.jsmr.domain.profile.custom.ProfileCustomRepository;
import mashup.spring.jsmr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileCustomRepository {
    
    Optional<Profile> findAllByUserId(Long userId);

    @Query("select p from Profile p join fetch p.user u where u.id = :userId")
    Optional<Profile> findByUserId(@Param("userId") Long userId);

    boolean existsProfileByUser(User user);
}
