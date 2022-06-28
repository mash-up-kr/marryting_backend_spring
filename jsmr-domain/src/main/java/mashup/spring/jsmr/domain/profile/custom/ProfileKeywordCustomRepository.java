package mashup.spring.jsmr.domain.profile.custom;

import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;

public interface ProfileKeywordCustomRepository {

    List<Profile> findByProfileKeywordByFetch(Long userId, Profile profile);
}
