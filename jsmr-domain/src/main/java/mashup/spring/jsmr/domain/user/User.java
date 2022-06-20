package mashup.spring.jsmr.domain.user;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {
    
    private String kakaoId;

    private String appleId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private Integer age;

    private String birth;

    private String address;

    private String career;

}
