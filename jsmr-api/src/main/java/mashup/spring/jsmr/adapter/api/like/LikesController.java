package mashup.spring.jsmr.adapter.api.like;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.LikesApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@RestController
public class LikesController {

    private final LikesApplicationService likesFacade;

    @ApiOperation("내가 좋아요한 사람들 조회")
    @GetMapping("/profiles")
    public List<LikeProfilesResponseDTO> getMyLikesPeople(@LoginUserId Long userId) {
        return likesFacade.getLikesProfiles(userId);
    }
}
