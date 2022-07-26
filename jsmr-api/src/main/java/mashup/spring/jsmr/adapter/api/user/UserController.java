package mashup.spring.jsmr.adapter.api.user;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.jwt.dto.TokenResponseDTO;
import mashup.spring.jsmr.adapter.api.user.dto.LoginRequestDTO;
import mashup.spring.jsmr.adapter.api.user.dto.SignupRequestDTO;
import mashup.spring.jsmr.application.UserApplicationService;
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
    public ApiResponse<TokenResponseDTO> login(@RequestBody LoginRequestDTO request) {
        return ApiResponse.success(
                HttpStatus.OK,
                userApplicationService.login(request.getType(), request.getThirdPartyToken())
        );
    }

    @ApiOperation("프로필 생성 및 회원가입")
    @PostMapping("/signup")
    public ApiResponse<TokenResponseDTO> signup(@RequestBody SignupRequestDTO request) {
        // TODO Implement creating profile logic
        return ApiResponse.success(
                HttpStatus.CREATED,
                userApplicationService.signup(request.getType(), request.getThirdPartyToken())
        );
    }
}