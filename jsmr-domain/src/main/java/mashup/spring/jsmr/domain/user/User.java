package mashup.spring.jsmr.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
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
        return new User(kakaoId, null, SocialType.KAKAO);
    }

    public static User createAppleUser(String appleId) {
        return new User(null, appleId, SocialType.APPLE);
    }
}
