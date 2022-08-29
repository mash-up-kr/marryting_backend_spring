package mashup.spring.jsmr.adapter.api.like;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeRequestDTO;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeResponseDTO;
import mashup.spring.jsmr.application.like.LikesApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/like")
@RequiredArgsConstructor
@RestController
public class LikesController {

    private final LikesApplicationService likesApplicationService;

    @ApiOperation("좋아요 생성")
    @PostMapping
    public ApiResponse<CreateLikeResponseDTO> createLike(@RequestBody CreateLikeRequestDTO createLikeRequestDTO) {
        return ApiResponse.success(HttpStatus.CREATED, likesApplicationService.createLike(createLikeRequestDTO));
    }
}
