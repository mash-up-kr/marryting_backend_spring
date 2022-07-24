package mashup.spring.jsmr.domain.wedding;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WeddingService {

    private final WeddingChannelRepository weddingChannelRepository;
    private final ProfileRepository profileRepository;

    public List<Wedding> getWeddingParticipateList(Long userId) {
        Profile profile = profileRepository.findByUserIdByQuerydsl(userId);
        if (profile == null) {
            throw new EntityNotFoundException();
        }

        return weddingChannelRepository.findAllByProfile(profile).stream()
                .map(WeddingChannel::getWedding)
                .collect(Collectors.toList());
    }
}
