package mashup.spring.jsmr.adapter.api.jwt;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.infrastructure.jwt.JwtProvider;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtProvider jwtProvider;

    @ApiOperation("테스트 토큰 발급")
    @GetMapping("/{userId}")
    public ApiResponse<String> getToken(@PathVariable Long userId) {
        return ApiResponse.success(HttpStatus.OK, jwtProvider.createAccessToken(userId));
    }
}
