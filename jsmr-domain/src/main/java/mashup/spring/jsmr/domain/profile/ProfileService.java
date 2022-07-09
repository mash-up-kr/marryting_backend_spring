package mashup.spring.jsmr.domain.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.answer.Answer;
import mashup.spring.jsmr.domain.answer.AnswerRepository;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.keyword.Keyword;
import mashup.spring.jsmr.domain.keyword.KeywordRepository;
import mashup.spring.jsmr.domain.question.QuestionRepository;
import mashup.spring.jsmr.domain.question.Questionnaire;
import mashup.spring.jsmr.domain.user.Gender;
import mashup.spring.jsmr.domain.user.User;
import mashup.spring.jsmr.domain.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class ProfileService {

    private final ProfileKeywordRepository profileKeywordRepository;
    private final ProfileRepository profileRepository;
    private final QuestionRepository questionRepository;
    private final KeywordRepository keywordRepository;
    private final UserRepository userRepository;
    private final AnswerRepository answerRepository;

    public List<Profile> getDetailProfile(final Long userId, final Long profileId) {
        Profile profile = profileRepository.findById(profileId).orElseThrow(EntityNotFoundException::new);
        return profileKeywordRepository.findByProfileByFetch(userId, profile);
    }

    public List<Questionnaire> getQuestionnaire() {
        List<Questionnaire> questionnaires = questionRepository.findAll();
        return questionnaires;
    }

    public List<Keyword> getKeyword() {
        List<Keyword> keywords = keywordRepository.findAll();

        return keywords;
    }

    @Transactional
    public Profile createProfile(Profile profile) {
        return profileRepository.save(profile);
    }
}
