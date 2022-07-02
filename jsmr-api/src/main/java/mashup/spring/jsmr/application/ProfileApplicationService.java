package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.ApiResponse;
import mashup.spring.jsmr.adapter.api.profile.dto.ProfileDetailResponseDTO;
import mashup.spring.jsmr.adapter.api.profile.dto.QuestionResponseDTO;
import mashup.spring.jsmr.domain.profile.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfileApplicationService {

    private final ProfileService profileService;

    public List<ProfileDetailResponseDTO> getDetailProfile(final Long userId, final Long profileId) {
        return profileService.getDetailProfile(userId, profileId).stream()
                .map(ProfileDetailResponseDTO::from)
                .collect(Collectors.toList());
    }

    public ApiResponse<List<QuestionResponseDTO>> getQuestionnare() {
        List<QuestionResponseDTO> questionResponseDTOS = profileService.getQuestionnaire().stream()
                .map(QuestionResponseDTO::from)
                .collect(Collectors.toList());

        return new ApiResponse<>(HttpStatus.OK.value(), questionResponseDTOS);
    }

}
