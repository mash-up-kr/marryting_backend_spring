package mashup.spring.jsmr.adapter.api.profile;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.CreateProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.profile.ProfileApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@RestController
public class ProfileController {

    private final ProfileApplicationService profileApplicationService;

    @ApiOperation("프로필 상세 조회")
    @GetMapping("/{profileId}/detail")
    public ApiResponse<ProfileDetailResponseDTO> getProfileDetail(@ApiIgnore @LoginUserId Long userId,
                                                                  @PathVariable Long profileId) {
        return ApiResponse.success(HttpStatus.OK, profileApplicationService.getDetailProfile(profileId));
    }

    @ApiOperation("프로필 생성")
    @PostMapping
    public ApiResponse<CreateProfileResponseDTO> createProfile(@ApiIgnore @LoginUserId Long userId,
                                                               @RequestBody CreateProfileRequestDTO createProfileRequestDTO) {
        return ApiResponse.success(HttpStatus.CREATED, profileApplicationService.createProfile(userId, createProfileRequestDTO));
    }

}