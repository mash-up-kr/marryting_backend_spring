package mashup.spring.jsmr.adapter.api.profile.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import mashup.spring.jsmr.adapter.api.answer.dto.CreateAnswerRequestDTO;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.user.User;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateProfileRequestDTO {
    private String name;
    private Gender gender;
    private String career;
    private String birth;
    private String address;
    private List<String> pictures;
    private List<CreateAnswerRequestDTO> answers;
    private List<String> keywords;

    public Profile toEntity(User user) {
        return Profile.builder()
                .name(name)
                .gender(gender)
                .career(career)
                .birth(birth)
                .address(address)
                .user(user)
                .build();
    }
}

