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

    private Long kakaoId;

    private String appleId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    public static User createKakaoUser(Long kakaoId) {
        return User.builder()
                .kakaoId(kakaoId)
                .socialType(SocialType.KAKAO)
                .build();
    }

    public static User createAppleUser(String appleId) {
        return User.builder()
                .appleId(appleId)
                .socialType(SocialType.APPLE)
                .build();
    }
}
