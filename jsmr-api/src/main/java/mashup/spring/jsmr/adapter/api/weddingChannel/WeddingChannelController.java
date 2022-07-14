package mashup.spring.jsmr.adapter.api.weddingChannel;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.WeddingChannelApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/wedding")
@RequiredArgsConstructor
@RestController
public class WeddingChannelController {

    private final WeddingChannelApplicationService weddingChannelApplicationService;

    @ApiOperation("결혼식에 참석하는 소개팅 리스트 조회")
    @GetMapping("/guests")
    public void getWeddingGuests(@LoginUserId Long userId) {
        weddingChannelApplicationService.getWeddingGuests(userId);
    }

}
