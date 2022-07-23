package mashup.spring.jsmr.domain.profile.custom;

import mashup.spring.jsmr.domain.profile.Profile;

public interface ProfileCustomRepository {

    Profile findByUserIdByQuerydsl(Long userId);
}