package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.dto.CreateAnswerRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.api.profileKeyword.dto.CreateProfileKeywordRequestDTO;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.answer.AnswerService;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.keyword.KeywordService;
import mashup.spring.jsmr.domain.picture.PictureService;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileService;
import mashup.spring.jsmr.domain.question.Questionnaire;
import mashup.spring.jsmr.domain.question.QuestionnareService;
import mashup.spring.jsmr.domain.user.User;
import mashup.spring.jsmr.domain.user.UserService;
import mashup.spring.jsmr.profileKeyword.ProfileKeywordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfileApplicationService {

    private final ProfileService profileService;
    private final UserService userService;
    private final ProfileKeywordService profileKeywordService;
    private final PictureService pictureService;
    private final AnswerService answerService;
    private final KeywordService keywordService;
    private final QuestionnareService questionnareService;


    public List<ProfileDetailResponseDTO> getDetailProfile(final Long userId, final Long profileId) {
        return profileService.getDetailProfile(userId, profileId).stream()
                .map(ProfileDetailResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public CreateProfileResponseDTO createProfile(Long userId, CreateProfileRequestDTO createProfileRequestDTO) {
        User user = userService.findById(userId);

        // profile save
        Profile profile = profileService.createProfile(createProfileRequestDTO.toEntity(user));

        // profile keywords save
        List<Keyword> choosedKeywords = keywordService.getChoosedKeyword(
                createProfileRequestDTO.getKeywords().stream()
                        .map(CreateProfileKeywordRequestDTO::getKeywordId)
                        .collect(Collectors.toList())
        );
        profileKeywordService.saveAll(choosedKeywords, profile);

        // profile picture save
        pictureService.saveAll(createProfileRequestDTO.getPictures(), profile);

        // profile question answer save
        List<CreateAnswerRequestDTO> createAnswerRequestDTOS = createProfileRequestDTO.getAnswers();
        List<Questionnaire> questionnaires = questionnareService.getQuestionnaires(
                createProfileRequestDTO.getAnswers().stream()
                        .map(CreateAnswerRequestDTO::getQuestionId)
                        .collect(Collectors.toList())
        );
        List<Answer> answers = new ArrayList<>();
        for (CreateAnswerRequestDTO createAnswerRequestDTO : createAnswerRequestDTOS) {
            for (Questionnaire questionnaire : questionnaires) {
                if (questionnaire.getId().equals(createAnswerRequestDTO.getQuestionId())) {
                    Answer answer = createAnswerRequestDTO.toEntity(profile, questionnaire);
                    answers.add(answer);
                }
            }
        }
        answerService.saveAll(answers);

        return CreateProfileResponseDTO.from(profile);
    }
}
