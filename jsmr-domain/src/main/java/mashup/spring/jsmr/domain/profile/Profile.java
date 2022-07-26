package mashup.spring.jsmr.domain.profile;

import lombok.*;
import mashup.spring.jsmr.domain.BaseEntity;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeyword;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.user.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Profile extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private String birth;

    private String address;

    private String career;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "profile")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<ProfileKeyword> profileKeywords = new ArrayList<>();

    @OneToMany(mappedBy = "profile")
    private List<Picture> pictures = new ArrayList<>();
}
