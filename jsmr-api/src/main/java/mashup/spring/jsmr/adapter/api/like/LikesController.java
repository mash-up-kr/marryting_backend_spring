package mashup.spring.jsmr.adapter.api.like;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeRequestDTO;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.MatchingProfileResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.like.LikesApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@RestController
public class LikesController {

    private final LikesApplicationService likesApplicationService;

    @ApiOperation("내가 좋아요한 사람들 조회")
    @GetMapping("/profiles")
    public ApiResponse<List<LikeProfilesResponseDTO>>getMyLikesPeople(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, likesApplicationService.getLikesProfiles(userId));
    }

    @ApiOperation("매칭된 사람들 조회")
    @GetMapping("/matching-profiles")
    public ApiResponse<List<MatchingProfileResponseDTO>> getMyMatchingPeople(@ApiIgnore @LoginUserId Long userId) {
        return ApiResponse.success(HttpStatus.OK, likesApplicationService.getMatchingProfiles(userId));
    }

    @ApiOperation("좋아요 생성")
    @PostMapping
    public ApiResponse<CreateLikeResponseDTO> createLike(@RequestBody CreateLikeRequestDTO createLikeRequestDTO) {
        return ApiResponse.success(HttpStatus.CREATED, likesApplicationService.createLike(createLikeRequestDTO));
    }
}
