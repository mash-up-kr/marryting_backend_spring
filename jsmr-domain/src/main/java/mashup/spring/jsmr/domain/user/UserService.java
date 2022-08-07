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
            Long kakaoId = authService.verifyKakao(thirdPartyToken).getKakaoId();
            return userRepository.findUserByKakaoIdAndSocialType(kakaoId, SocialType.KAKAO)
                    .orElseThrow(EntityNotFoundException::new);
        } else if (socialType.equals(SocialType.APPLE.name())) {
            String appleId = authService.verifyApple(thirdPartyToken);
            return userRepository.findUserByAppleIdAndSocialType(appleId, SocialType.APPLE)
                    .orElseThrow(EntityNotFoundException::new);
        } else {
            throw new BusinessException(ExceptionCode.INVALID_INPUT_VALUE);
        }
    }

    @Transactional
    public User signup(String socialType, String thirdPartyToken) {
        if (socialType.equals(SocialType.KAKAO.name())) {
            Long kakaoId = authService.verifyKakao(thirdPartyToken).getKakaoId();
            userRepository.findUserByKakaoIdAndSocialType(kakaoId, SocialType.KAKAO)
                    .ifPresent(user -> {
                        throw new BusinessException(ExceptionCode.USER_DUPLICATED);
                    });
            return userRepository.save(User.createKakaoUser(kakaoId));
        } else if (socialType.equals(SocialType.APPLE.name())) {
            String appleId = authService.verifyApple(thirdPartyToken);
            userRepository.findUserByAppleIdAndSocialType(appleId, SocialType.APPLE)
                    .ifPresent(user -> {
                        throw new BusinessException(ExceptionCode.USER_DUPLICATED);
                    });
            return userRepository.save(User.createAppleUser(appleId));
        } else {
            throw new BusinessException(ExceptionCode.INVALID_INPUT_VALUE);
        }
    }

    public User findById(final Long userId) {
        return userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }
}
