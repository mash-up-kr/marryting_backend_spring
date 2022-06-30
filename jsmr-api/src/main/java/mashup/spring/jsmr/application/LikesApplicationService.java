package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.MatchingProfileResponseDTO;
import mashup.spring.jsmr.domain.like.LikesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesApplicationService {

    private final LikesService likesService;

    public List<LikeProfilesResponseDTO> getLikesProfiles(final Long userId) {
        return likesService.getMyLikesProfile(userId).stream()
                .map(LikeProfilesResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<MatchingProfileResponseDTO> getMatchingProfiles(final Long userId){
        return likesService.getMyMatchingProfiles(userId).stream()
                .map(profile -> {
                    Long partnerId = profile.getUser().getId();
                    String matchingMessage = likesService.getMatchingMessage(userId, partnerId);
                    return MatchingProfileResponseDTO.from(profile,matchingMessage);
                })
                .collect(Collectors.toList());
    }
    public long getTotalMatchingNumber(){
        return likesService.getTotalMatchingNumber();
    }
}
