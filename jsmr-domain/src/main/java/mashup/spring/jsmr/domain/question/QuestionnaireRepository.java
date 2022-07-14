package mashup.spring.jsmr.domain.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {

    List<Questionnaire> findAllByIdIn(List<Long> questionnairesId);
}
