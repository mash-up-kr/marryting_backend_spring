package mashup.spring.jsmr.adapter.api.wedding;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.wedding.dto.CreateWeddingRequestDTO;
import mashup.spring.jsmr.adapter.api.wedding.dto.WeddingParticipateListDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.wedding.WeddingApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/v1/wedding")
@RequiredArgsConstructor
@RestController
public class WeddingController {

    private final WeddingApplicationService weddingApplicationService;

    @ApiOperation("내가 참여했던 결혼식 리스트")
    @GetMapping("/guest/list")
    public ApiResponse<List<WeddingParticipateListDTO>> getWeddingList(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, weddingApplicationService.getWeddingParticipateList(userId));
    }

    @ApiOperation("결혼식 생성하기")
    @PostMapping
    public ApiResponse<Void> createWeddingChannel(@Valid @RequestBody CreateWeddingRequestDTO weddingRequestDTO) {
        weddingApplicationService.createWeddingChannel(weddingRequestDTO);
        return ApiResponse.success(HttpStatus.CREATED);
    }
}
