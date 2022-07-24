package mashup.spring.jsmr.domain.profileKeyword.custom;

import mashup.spring.jsmr.domain.profile.Profile;

import java.util.List;

public interface ProfileKeywordCustomRepository {

    List<Profile> findByProfileByFetch(Long userId, Profile profile);
}
