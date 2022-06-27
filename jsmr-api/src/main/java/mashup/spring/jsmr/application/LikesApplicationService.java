package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesResponseDTO;
import mashup.spring.jsmr.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesApplicationService {

    private final LikeService likeService;

    public List<LikeProfilesResponseDTO> getLikesProfiles(final Long userId) {
        return likeService.getMyLikesProfile(userId).stream()
                .map(LikeProfilesResponseDTO::from)
                .collect(Collectors.toList());
    }

}
