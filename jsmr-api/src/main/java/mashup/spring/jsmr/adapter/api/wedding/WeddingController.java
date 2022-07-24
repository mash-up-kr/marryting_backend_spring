package mashup.spring.jsmr.adapter.api.wedding;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.wedding.dto.WeddingParticipateListDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.wedding.WeddingApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/wedding")
@RequiredArgsConstructor
@RestController
public class WeddingController {

    private final WeddingApplicationService weddingApplicationService;

    @GetMapping("/participate/list")
    public ApiResponse<List<WeddingParticipateListDTO>> getWeddingList(@LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, weddingApplicationService.getWeddingParticipateList(userId));
    }
}
