package mashup.spring.jsmr.adapter.api;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.jwt.TokenResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.jwt.JwtProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AdviceController {

    private final JwtProvider jwtProvider;

    @GetMapping("/server-error")
    public void serverError() throws Exception {
        throw new Exception("서버 에러");
    }

    @GetMapping("/jwt-create")
    public TokenResponseDTO createJwtTest() {
        return jwtProvider.createTokenResponse(1L);
    }

}
