package mashup.spring.jsmr.domain.profileKeyword;

import mashup.spring.jsmr.domain.profileKeyword.custom.ProfileKeywordCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileKeywordRepository extends JpaRepository<ProfileKeyword, Long>, ProfileKeywordCustomRepository {
}
