package mashup.spring.jsmr.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.domain.auth.OAuthService;
import mashup.spring.jsmr.domain.exception.BusinessException;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final OAuthService authService;
    private final UserRepository userRepository;

    public User login(String socialType, String thirdPartyToken) {
        if (socialType.equals(SocialType.KAKAO.name())) {
            String kakaoId = authService.verifyKakao(thirdPartyToken);
            return userRepository.findUserBySocialIdAndSocialType(kakaoId, SocialType.KAKAO)
                    .orElseThrow(EntityNotFoundException::new);
        } else if (socialType.equals(SocialType.APPLE.name())) {
            String appleId = authService.verifyApple(thirdPartyToken);
            return userRepository.findUserBySocialIdAndSocialType(appleId, SocialType.APPLE)
                    .orElseThrow(EntityNotFoundException::new);
        }
        throw new BusinessException(ExceptionCode.INVALID_INPUT_VALUE);
    }

    @Transactional
    public User signup(String socialType, String thirdPartyToken) {
        if (socialType.equals(SocialType.KAKAO.name())) {
            String kakaoId = authService.verifyKakao(thirdPartyToken);
            userRepository.findUserBySocialIdAndSocialType(kakaoId, SocialType.KAKAO)
                    .ifPresent(user -> {
                        throw new BusinessException(ExceptionCode.EXIST_ENTITY);
                    });
            return userRepository.save(User.createKakaoUser(kakaoId));
        } else if (socialType.equals(SocialType.APPLE.name())) {
            String appleId = authService.verifyApple(thirdPartyToken);
            userRepository.findUserBySocialIdAndSocialType(appleId, SocialType.APPLE)
                    .ifPresent(user -> {
                        throw new BusinessException(ExceptionCode.EXIST_ENTITY);
                    });
            return userRepository.save(User.createAppleUser(appleId));
        }
        throw new BusinessException(ExceptionCode.INVALID_INPUT_VALUE);
    }

    public User findById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
