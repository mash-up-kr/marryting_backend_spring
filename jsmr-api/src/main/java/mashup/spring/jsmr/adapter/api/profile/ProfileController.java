package mashup.spring.jsmr.adapter.api.profile;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.keyword.dto.KeywordResponseDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.*;
import mashup.spring.jsmr.adapter.api.questionnare.dto.QuestionnaireResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.ProfileApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    @GetMapping("/{profileId}/detail")
    public List<ProfileDetailResponseDTO> getProfileDetail(@LoginUserId Long userId,
                                                           @PathVariable Long profileId) {
        return profileApplicationService.getDetailProfile(userId, profileId);
    }

    @ApiOperation("질문지 확인")
    @GetMapping("/questionnaire")
    public ApiResponse<List<QuestionnaireResponseDTO>> getProfileQuestionnaire() {

        return ApiResponse.success(HttpStatus.OK, profileApplicationService.getQuestionnare());
    }

    @ApiOperation("키워드 확인")
    @GetMapping("/keywords")
    public ApiResponse<List<KeywordResponseDTO>> getProfileKeyword() {

        return ApiResponse.success(HttpStatus.OK, profileApplicationService.getKeyword());
    }

    @ApiOperation("프로필 생성")
    @PostMapping
    public ApiResponse<CreateProfileResponseDTO> createProfile(@LoginUserId Long userId, @RequestBody CreateProfileRequestDTO createProfileRequestDTO) {

        return ApiResponse.success(HttpStatus.CREATED, profileApplicationService.createProfile(userId, createProfileRequestDTO));
    }

}