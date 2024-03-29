package mashup.spring.jsmr.domain.profile;

import mashup.spring.jsmr.domain.profile.custom.ProfileCustomRepository;
import mashup.spring.jsmr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileCustomRepository {
    
    Optional<Profile> findAllByUserId(Long userId);

    boolean existsProfileByUser(User user);

    void deleteByUser(User user);
}
