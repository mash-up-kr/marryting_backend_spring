package mashup.spring.jsmr.application.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.dto.CreateAnswerRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.api.profileKeyword.dto.CreateProfileKeywordRequestDTO;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.answer.AnswerService;
import mashup.spring.jsmr.domain.exception.DuplicatedException;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.keyword.KeywordService;
import mashup.spring.jsmr.domain.picture.Picture;
import mashup.spring.jsmr.domain.picture.PictureService;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileService;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeyword;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeywordService;
import mashup.spring.jsmr.domain.user.User;
import mashup.spring.jsmr.domain.user.UserService;
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

    public ProfileDetailResponseDTO getDetailProfileById(final Long profileId) {
        return ProfileDetailResponseDTO.from(profileService.getDetailProfile(profileId));
    }

    public ProfileDetailResponseDTO getDetailProfileByUserId(final Long userId) {
        return ProfileDetailResponseDTO.from(profileService.getProfile(userId));
    }

    @Transactional
    public ProfileDetailResponseDTO createProfile(User user, CreateProfileRequestDTO createProfileRequestDTO) {
        if (profileService.existProfile(user)) {
            throw new DuplicatedException();
        }

        // profile save
        Profile profile = profileService.createProfile(createProfileRequestDTO.toEntity(user));

        // profile keywords save
        List<Keyword> choosedKeywords = keywordService.getChoosedKeyword(
                createProfileRequestDTO.getKeywords().stream()
                        .map(CreateProfileKeywordRequestDTO::getKeywordId)
                        .collect(Collectors.toList())
        );
        List<ProfileKeyword> saveProfileKeywords = profileKeywordService.saveAll(choosedKeywords, profile);

        // profile picture save
        List<Picture> savePictures = pictureService.saveAll(createProfileRequestDTO.getPictures(), profile);

        // answer save
        List<CreateAnswerRequestDTO> createAnswerRequestDTOS = createProfileRequestDTO.getAnswers();
        List<Answer> answers = new ArrayList<>();
        for (CreateAnswerRequestDTO createAnswerRequestDTO : createAnswerRequestDTOS) {
            Answer answer = createAnswerRequestDTO.toEntity(profile);
            answers.add(answer);
        }

        List<Answer> saveAnswers = answerService.saveAll(answers);

        return ProfileDetailResponseDTO.from(profile, saveProfileKeywords, saveAnswers, savePictures);
    }
}
