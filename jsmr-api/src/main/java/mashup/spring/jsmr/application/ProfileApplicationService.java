package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.answer.AnswerCreateRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileCreateRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.answer.AnswerService;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.keyword.KeywordService;
import mashup.spring.jsmr.domain.picture.PictureService;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileService;
import mashup.spring.jsmr.domain.profileKeyword.ProfileKeywordService;
import mashup.spring.jsmr.domain.question.QuestionnaireService;
import mashup.spring.jsmr.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileApplicationService {

    private final ProfileService profileService;
    private final UserService userService;

    private final KeywordService keywordService;
    private final ProfileKeywordService profileKeywordService;
    private final PictureService pictureService;
    private final AnswerService answerService;
    private final QuestionnaireService questionnaireService;

    public List<ProfileDetailResponseDTO> getDetailProfile(final Long userId, final Long profileId) {
        return profileService.getDetailProfile(userId, profileId).stream()
                .map(ProfileDetailResponseDTO::from)
                .collect(Collectors.toList());
    }

    @Transactional
    public void createProfile(ProfileCreateRequestDTO createRequestDTO, Long userId) {
        Profile profile = profileService.createProfile(createRequestDTO.toEntity(userService.findById(userId)));
        List<Keyword> keywords = keywordService.getUserKeyword(createRequestDTO.getKeywords());
        profileKeywordService.saveAll(keywords, profile); // ProfileKeyword SaveAll
        pictureService.saveAll(createRequestDTO.getPictures(), profile); // profile Picture saveAll

        List<AnswerCreateRequestDTO> answerCreateRequestDTOS = createRequestDTO.getAnswerCreateRequestDTOS();
        List<Answer> answers = new ArrayList<>();
        for (AnswerCreateRequestDTO answerCreateRequestDTO : answerCreateRequestDTOS) {
            answers.add(answerCreateRequestDTO.toEntity(profile));
        }

        answerService.saveAll(answers);
    }
}
