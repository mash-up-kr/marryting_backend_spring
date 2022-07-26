package mashup.spring.jsmr.adapter.api.profile.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProfileDetailResponseDTO {

    private Long profileId;
    private String profileName;
    private Integer age;
    private String address;
    private String career;
    private List<String> keywords;
    private List<String> answers;
    private List<String> pictures;

    @Builder
    public ProfileDetailResponseDTO(
            Long profileId,
            String profileName,
            Integer age,
            String address,
            String career,
            List<String> keywords,
            List<String> answers,
            List<String> pictures
    ) {
        this.profileId = profileId;
        this.profileName = profileName;
        this.age = age;
        this.address = address;
        this.career = career;
        this.keywords = keywords;
        this.answers = answers;
        this.pictures = pictures;
    }

    public static ProfileDetailResponseDTO from(Profile profile) {
        int nowYear = LocalDate.now().getYear();
        int age = nowYear - Integer.parseInt(profile.getBirth().substring(0, 4)) + 1;

        return ProfileDetailResponseDTO.builder()
                .profileId(profile.getId())
                .profileName(profile.getName())
                .age(age)
                .address(profile.getAddress())
                .career(profile.getCareer())
                .keywords(profile.getProfileKeywords().stream()
                        .map(p -> p.getKeyword().getKeyword())
                        .collect(Collectors.toList()))
                .answers(profile.getAnswers().stream()
                        .map(Answer::getAnswer)
                        .collect(Collectors.toList()))
                .pictures(profile.getPictures().stream()
                        .map(Picture::getProfileUrl)
                        .collect(Collectors.toList()))
                .build();
    }
}
