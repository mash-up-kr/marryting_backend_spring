package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.jwt.dto.TokenResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.jwt.JwtProvider;
import mashup.spring.jsmr.domain.user.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserApplicationService {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    public TokenResponseDTO login(String socialType, String thirdPartyToken) {
        return jwtProvider.createTokenResponse(
                userService.login(thirdPartyToken, socialType).getId()
        );
    }

    public TokenResponseDTO signup(String socialType, String thirdPartyToken) {
        return jwtProvider.createTokenResponse(
                userService.signup(socialType, thirdPartyToken).getId()
        );
    }
}
