package mashup.spring.jsmr.adapter.api.like;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
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
    public List<LikeProfilesResponseDTO> getMyLikesPeople() {
        return likesFacade.getLikesProfiles(1L); // 추후 로그인 완료되면 userId 넣기
    }
}