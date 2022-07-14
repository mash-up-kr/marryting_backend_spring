package mashup.spring.jsmr.domain.weddingChannel;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WeddingChannelService {

    private final WeddingChannelRepository weddingChannelRepository;
    private final ProfileRepository profileRepository;

    public List<WeddingChannel> getWeddingGuests(final Long userId) {
        Profile profile = profileRepository.findAllByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        return weddingChannelRepository.findByWeddingGuestsByFetch(profile.getGender());
    }

}
