package mashup.spring.jsmr.adapter.api.profile;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.infrastructure.interceptor.LoginUserId;
import mashup.spring.jsmr.application.ProfileApplicationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
