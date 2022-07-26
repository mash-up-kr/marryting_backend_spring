package mashup.spring.jsmr.domain.answer;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.profile.Profile;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Answer extends BaseEntity {

    private String answer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private Profile profile;

    private Long questionnaireId;
}
