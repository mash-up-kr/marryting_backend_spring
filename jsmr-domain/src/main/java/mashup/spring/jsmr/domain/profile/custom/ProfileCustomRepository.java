package mashup.spring.jsmr.domain.profile.custom;

import mashup.spring.jsmr.domain.profile.Profile;

import java.util.Optional;

public interface ProfileCustomRepository {

    Optional<Profile> findByUserIdByQuerydsl(Long userId);
}
