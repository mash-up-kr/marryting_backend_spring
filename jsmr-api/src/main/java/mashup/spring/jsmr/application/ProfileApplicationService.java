package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.*;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.answer.AnswerService;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.picture.PictureService;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileKeyword;
import mashup.spring.jsmr.domain.profile.ProfileKeywordService;
import mashup.spring.jsmr.domain.profile.ProfileService;
import mashup.spring.jsmr.domain.user.UserService;
import org.springframework.stereotype.Service;

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


    public List<ProfileDetailResponseDTO> getDetailProfile(final Long userId, final Long profileId) {
        return profileService.getDetailProfile(userId, profileId).stream()
                .map(ProfileDetailResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<QuestionResponseDTO> getQuestionnare() {
        return profileService.getQuestionnaire().stream()
                .map(QuestionResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<KeywordResponseDTO> getKeyword() {
        return profileService.getKeyword().stream()
                .map(KeywordResponseDTO::from)
                .collect(Collectors.toList());
    }

    public CreateProfileResponseDTO createProfile(Long userId, CreateProfileRequestDTO createProfileRequestDTO) {
        Profile profile = profileService.createProfile(createProfileRequestDTO.toProfileEntity(userService.getOne(userId)));

        List<ProfileKeyword> profileKeywords = createProfileRequestDTO.toProfileKeywordEntity(profile);
        profileKeywordService.saveAll(profileKeywords);
        List<Picture> pictures = createProfileRequestDTO.toPictureEntity(profile);
        pictureService.saveAll(pictures);
        List<Answer> answers = createProfileRequestDTO.toAnswerEntity(profile);
        answerService.saveAll(answers);

        return new CreateProfileResponseDTO(profile.getId());
    }

}
