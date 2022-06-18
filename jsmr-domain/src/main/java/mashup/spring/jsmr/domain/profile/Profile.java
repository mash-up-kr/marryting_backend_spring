package mashup.spring.jsmr.domain.profile;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.user.User;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Profile extends BaseEntity {

    private String picture1;

    private String picture2;

    private String picture3;

    private String keyword1;

    private String keyword2;

    private String keyword3;

    private String answer1;

    private String answer2;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}
