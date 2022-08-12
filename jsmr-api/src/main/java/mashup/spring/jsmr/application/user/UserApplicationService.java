package mashup.spring.jsmr.application.user;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.LoginResponseDTO;
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

    public LoginResponseDTO login(String socialType, String thirdPartyToken) {
        User user = userService.login(socialType, thirdPartyToken);
        ProfileDetailResponseDTO profile = profileApplicationService.getDetailProfileByUserId(user.getId());
        return LoginResponseDTO.from(
                jwtProvider.createAccessToken(user.getId()),
                profile
        );
    }

    public CreateUserWithProfileResponseDTO signup(CreateUserWithProfileRequestDTO request) {
        User user = userService.signup(request.getOauthType(), request.getThirdPartyToken());
        CreateProfileResponseDTO profile = profileApplicationService.createProfile(user.getId(), request.getProfile());
        return CreateUserWithProfileResponseDTO.from(
                jwtProvider.createAccessToken(user.getId()),
                profile.getProfileId()
        );
    }
}
