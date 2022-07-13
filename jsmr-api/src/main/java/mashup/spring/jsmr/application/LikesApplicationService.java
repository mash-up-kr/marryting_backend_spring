package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeRequestDTO;
import mashup.spring.jsmr.adapter.api.like.dto.CreateLikeResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.adapter.api.like.dto.MatchingProfileResponseDTO;
import mashup.spring.jsmr.domain.like.Likes;
import mashup.spring.jsmr.domain.like.LikesService;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesApplicationService {

    private final LikesService likesService;

    public List<LikeProfilesResponseDTO> getLikesProfiles(final Long profileId) {
        return likesService.getMyLikesProfile(profileId).stream()
                .map(LikeProfilesResponseDTO::from)
                .collect(Collectors.toList());
    }

    public List<MatchingProfileResponseDTO> getMatchingProfiles(final Long profileId) {
        return likesService.getMyMatchingProfiles(profileId).entrySet().stream()
                .map(e -> {
                    Profile profile = e.getKey();
                    String message = e.getValue();
                    return MatchingProfileResponseDTO.from(profile, message);
                })
                .collect(Collectors.toList());
    }

    public CreateLikeResponseDTO createLike(CreateLikeRequestDTO requestDTO) {
        Likes likes = likesService.createLike(requestDTO.getSenderProfileId(), requestDTO.getReceiverProfileId(), requestDTO.getMessage());
        return CreateLikeResponseDTO.from(likes);
    }

}
