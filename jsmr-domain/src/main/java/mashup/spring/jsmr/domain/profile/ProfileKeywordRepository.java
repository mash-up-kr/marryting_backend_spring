package mashup.spring.jsmr.domain.profile;

import mashup.spring.jsmr.domain.profile.custom.ProfileKeywordCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileKeywordRepository extends JpaRepository<ProfileKeyword, Long>, ProfileKeywordCustomRepository {
}