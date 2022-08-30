package mashup.spring.jsmr.domain.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.profile.Profile;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity {

    private String socialId;

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Profile profile;

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
