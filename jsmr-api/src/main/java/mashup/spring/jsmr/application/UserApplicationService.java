package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.TokenResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.jwt.JwtProvider;
import mashup.spring.jsmr.application.profile.ProfileApplicationService;
import mashup.spring.jsmr.domain.user.User;
import mashup.spring.jsmr.domain.user.UserService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserApplicationService {

    private final UserService userService;
    private final ProfileApplicationService profileApplicationService;
    private final JwtProvider jwtProvider;

    public TokenResponseDTO login(String socialType, String thirdPartyToken) {
        return TokenResponseDTO.from(
                jwtProvider.createAccessToken(
                        userService.login(socialType, thirdPartyToken).getId()
                )
        );
    }

    public CreateUserWithProfileResponseDTO signup(CreateUserWithProfileRequestDTO request) {
        User user = userService.signup(request.getLogin().getType(), request.getLogin().getThirdPartyToken());
        CreateProfileResponseDTO profile = profileApplicationService.createProfile(user.getId(), request.getProfile());
        return CreateUserWithProfileResponseDTO.from(
                jwtProvider.createAccessToken(user.getId()),
                profile.getProfileId()
        );
    }
}
