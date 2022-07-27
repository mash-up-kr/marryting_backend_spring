package mashup.spring.jsmr.adapter.api.weddingChannel;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.weddingChannel.dto.WeddingChannelGuestResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.weddingChannel.WeddingChannelApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/api/v1/wedding")
@RequiredArgsConstructor
@RestController
public class WeddingChannelController {

    private final WeddingChannelApplicationService weddingChannelApplicationService;

    @ApiOperation("결혼식에 참석하는 소개팅 리스트 조회")
    @GetMapping("/guests")
    public ApiResponse<List<WeddingChannelGuestResponseDTO>> getWeddingGuests(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, weddingChannelApplicationService.getWeddingGuests(userId));
    }

    @ApiOperation("결혼식 소개팅 생성")
    @PostMapping("/channel/{weddingId}")
    public void createWeddingChannel(@ApiIgnore @LoginUserId Long userId,
                                     @PathVariable Long weddingId) {
        weddingChannelApplicationService.createWeddingChannel(userId, weddingId);
    }
}
