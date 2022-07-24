package mashup.spring.jsmr.domain.wedding;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Wedding extends BaseEntity {

    private String groomName;

    private String brideName;

    private String weddingHallAddress;

    private LocalDate weddingDate;

}
