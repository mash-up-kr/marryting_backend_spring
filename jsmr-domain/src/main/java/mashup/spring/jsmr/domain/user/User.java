package mashup.spring.jsmr.domain.user;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {
    
    private String kakaoId;

    private String appleId;

    private String name;

    private Integer age;

    private String birth;

    private String address;

    private String career;

}
