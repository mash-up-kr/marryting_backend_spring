package mashup.spring.jsmr.application.wedding;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.MatchingProfileResponseDTO;
import mashup.spring.jsmr.adapter.api.wedding.dto.CreateWeddingRequestDTO;
import mashup.spring.jsmr.adapter.api.wedding.dto.CreateWeddingResponseDTO;
import mashup.spring.jsmr.adapter.api.wedding.dto.WeddingParticipateListDTO;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileService;
import mashup.spring.jsmr.domain.wedding.WeddingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class WeddingApplicationService {

    private final WeddingService weddingService;
    private final ProfileService profileService;

    public List<WeddingParticipateListDTO> getWeddingParticipateList(final Long userId) {
        return weddingService.getWeddingParticipateList(userId).stream()
                .map(WeddingParticipateListDTO::from)
                .collect(Collectors.toList());
    }

    public void deleteExpiredWeddings() {
        weddingService.deleteExpiredWeddingsAsOfToday();
    }

    public CreateWeddingResponseDTO createWeddingChannel(final CreateWeddingRequestDTO weddingRequestDTO) {
        return CreateWeddingResponseDTO.from(weddingService.createWedding(weddingRequestDTO.toEntity()));
    }

    public List<LikeProfilesResponseDTO> getLikesProfiles(final Long userId, final Long weddingId) {
        Long profileId = profileService.getProfile(userId).getId();

        return weddingService.getMyLikesProfile(profileId, weddingId).stream()
                .map(LikeProfilesResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<MatchingProfileResponseDTO> getMatchingProfiles(final Long userId, final Long weddingId) {
        Long profileId = profileService.getProfile(userId).getId();

        return weddingService.getMyMatchingProfiles(profileId, weddingId).entrySet().stream()
                .map(e -> {
                    Profile profile = e.getKey();
                    String message = e.getValue();
                    return MatchingProfileResponseDTO.from(profile, message);
                })
                .collect(Collectors.toList());
    }
}
