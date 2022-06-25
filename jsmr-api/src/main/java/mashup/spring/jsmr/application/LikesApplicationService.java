package mashup.spring.jsmr.application;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.adapter.api.like.dto.LikeProfilesDTO;
import mashup.spring.jsmr.service.LikeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LikesApplicationService {

    private final LikeService likeService;

    public List<LikeProfilesDTO> getLikesProfiles(final Long userId) {
        return likeService.getMyLikesProfile(userId).stream()
                .map(LikeProfilesDTO::from)
                .collect(Collectors.toList());
    }

}
