package mashup.spring.jsmr.domain.weddingChannel;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.DuplicatedException;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.like.LikesRepository;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.wedding.Wedding;
import mashup.spring.jsmr.domain.wedding.WeddingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WeddingChannelService {

    private final WeddingChannelRepository weddingChannelRepository;

    private final ProfileRepository profileRepository;

    private final LikesRepository likesRepository;

    private final WeddingRepository weddingRepository;

    public List<WeddingChannel> getWeddingGuests(final Long userId, final Long weddingId) {
        Profile profile = profileRepository.findAllByUserId(userId)
                .orElseThrow(EntityNotFoundException::new);

        List<Long> postedLikeList = likesRepository.findAllBySender(profile).stream()
                .map(likes -> likes.getReceiver().getId())
                .collect(Collectors.toList());

        if (postedLikeList.isEmpty()){ //빈 경우 query : not in (null) 방지
            postedLikeList.add(-1L);
        }
        return weddingChannelRepository.findByWeddingGuestsByFetch(profile, weddingId, postedLikeList);
    }

    @Transactional
    public WeddingChannel participateWeddingChannel(final Long userId, String weddingCode) {
        Profile profile = profileRepository.findByUserIdByQuerydsl(userId)
                .orElseThrow(EntityNotFoundException::new);
        Wedding wedding = weddingRepository.findByWeddingCode((weddingCode))
                .orElseThrow(EntityNotFoundException::new);
        if (weddingChannelRepository.existsWeddingChannelByProfile(profile)) {
            throw new DuplicatedException();
        };

        WeddingChannel weddingChannel = WeddingChannel.builder()
                .profile(profile)
                .wedding(wedding)
                .build();

        return weddingChannelRepository.save(weddingChannel);
    }
}
