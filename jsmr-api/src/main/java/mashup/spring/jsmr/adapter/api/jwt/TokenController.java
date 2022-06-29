package mashup.spring.jsmr.adapter.api.jwt;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.jwt.dto.TokenResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.jwt.JwtProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/token")
@RequiredArgsConstructor
@RestController
public class TokenController {

    private final JwtProvider jwtProvider;

    @GetMapping("/{userId}")
    public TokenResponseDTO getToken(@PathVariable Long userId) {
        return jwtProvider.createTokenResponse(userId);
    }
}
