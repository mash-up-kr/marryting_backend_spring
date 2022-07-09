package mashup.spring.jsmr.adapter.api.profile.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileKeyword;
import mashup.spring.jsmr.domain.question.Questionnaire;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.user.User;

import java.util.ArrayList;
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
    private List<AnswerRequestDTO> answers;
    private List<String> keywords;


    @Getter
    public static class AnswerRequestDTO {
        private String question;
        private String answer;
    }

    public Profile toProfileEntity(User user) {
        return Profile.builder()
                .name(name)
                .gender(gender)
                .career(career)
                .birth(birth)
                .address(address)
                .user(user)
                .build();
    }

    public List<ProfileKeyword> toProfileKeywordEntity(Profile profile) {
        List<Keyword> entityKeywords = new ArrayList<>();
        List<ProfileKeyword> entityProfileKeywords = new ArrayList<>();
        for (int i = 0; i < keywords.size(); ++i) {
            Keyword keyword = Keyword.builder()
                    .keyword(keywords.get(i))
                    .build();
            entityKeywords.add(keyword);
            entityProfileKeywords.add(
                    ProfileKeyword.builder()
                            .keyword(keyword)
                            .profile(profile)
                            .build()
            );
        }

        return entityProfileKeywords;
    }

    public List<Picture> toPictureEntity(Profile profile) {
        List<Picture> entityPictures = new ArrayList<>();
        for (int i = 0; i < pictures.size(); ++i) {
            entityPictures.add(
                    Picture.builder()
                            .profileUrl(pictures.get(i))
                            .profile(profile)
                            .build()
            );
        }

        return entityPictures;
    }

    public List<Answer> toAnswerEntity(Profile profile) {
        List<Questionnaire> entityQuestions = new ArrayList<>();
        List<Answer> entityAnswers = new ArrayList<>();
        for (int i = 0; i < answers.size(); i++) {
            Questionnaire questionnaire = Questionnaire.builder()
                    .question(answers.get(i).getQuestion())
                    .build();
            entityQuestions.add(questionnaire);
            entityAnswers.add(
                    Answer.builder()
                            .answer(answers.get(i).getAnswer())
                            .questionnaire(questionnaire)
                            .profile(profile)
                            .build()
            );
        }

        return entityAnswers;
    }
}

