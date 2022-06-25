package mashup.spring.jsmr.domain.question;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;

import javax.persistence.Entity;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Questionnaire extends BaseEntity {

    private String question;

    private String answer1;

    private String answer2;
}
