package mashup.spring.jsmr.adapter.api.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.user.dto.CreateUserWithProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.LoginRequestDTO;
import mashup.spring.jsmr.adapter.api.user.dto.LoginResponseDTO;
import mashup.spring.jsmr.application.user.UserApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/users")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserApplicationService userApplicationService;

    @ApiOperation("로그인")
    @PostMapping("/login")
    public ApiResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ApiResponse.success(
                HttpStatus.OK,
                userApplicationService.login(request.getOauthType(), request.getThirdPartyToken())
        );
    }

    @ApiOperation("프로필 생성 및 회원가입")
    @PostMapping("/signup")
    public ApiResponse<CreateUserWithProfileResponseDTO> signup(@RequestBody CreateUserWithProfileRequestDTO request) {
        return ApiResponse.success(
                HttpStatus.CREATED,
                userApplicationService.signup(request)
        );
    }
}