package mashup.spring.jsmr.domain.weddingChannel;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.wedding.Role;
import mashup.spring.jsmr.domain.wedding.Wedding;
import mashup.spring.jsmr.domain.wedding.WeddingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WeddingChannelService {

    private final WeddingChannelRepository weddingChannelRepository;
    private final WeddingRepository weddingRepository;
    private final ProfileRepository profileRepository;

    public List<WeddingChannel> getWeddingGuests(final Long userId) {
        Profile profile = profileRepository.findAllByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);
        return weddingChannelRepository.findByWeddingGuestsByFetch(profile);
    }

    @Transactional
    public void createWeddingChannel(final Long userId, final Long weddingId) {
        Profile profile = profileRepository.findByUserIdByQuerydsl(userId);
        if (profile == null) {
            throw new EntityNotFoundException();
        }

        Wedding wedding = weddingRepository.findById(weddingId)
                .orElseThrow(EntityNotFoundException::new);

        weddingChannelRepository.save(WeddingChannel.builder()
                .wedding(wedding)
                .profile(profile)
                .role(Role.HOST)
                .build());
    }
}
