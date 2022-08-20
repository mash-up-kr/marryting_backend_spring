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

    private String socialId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public static User createKakaoUser(String kakaoId) {
        return User.builder()
                .socialId(kakaoId)
                .socialType(SocialType.KAKAO)
                .build();
    }

    public static User createAppleUser(String appleId) {
        return User.builder()
                .socialId(appleId)
                .socialType(SocialType.APPLE)
                .build();
    }
}
