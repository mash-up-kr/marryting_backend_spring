package mashup.spring.jsmr.profileKeyword;

import mashup.spring.jsmr.domain.profile.custom.ProfileKeywordCustomRepository;
import mashup.spring.jsmr.profileKeyword.ProfileKeyword;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileKeywordRepository extends JpaRepository<ProfileKeyword, Long>, ProfileKeywordCustomRepository {
}
