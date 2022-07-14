package mashup.spring.jsmr.adapter.api.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiV1;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileCreateRequestDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.ProfileApplicationService;
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

    @PostMapping
    public String createProfile(@RequestBody ProfileCreateRequestDTO createRequestDTO, @LoginUserId Long userId) {
        profileApplicationService.createProfile(createRequestDTO, userId);
        return "test";
    }

}
