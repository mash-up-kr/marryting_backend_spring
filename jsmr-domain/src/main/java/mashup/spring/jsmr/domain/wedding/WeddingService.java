package mashup.spring.jsmr.domain.wedding;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.exception.EntityNotFoundException;
import mashup.spring.jsmr.domain.like.Likes;
import mashup.spring.jsmr.domain.like.LikesRepository;
import mashup.spring.jsmr.domain.profile.Profile;
import mashup.spring.jsmr.domain.profile.ProfileRepository;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannel;
import mashup.spring.jsmr.domain.weddingChannel.WeddingChannelRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class WeddingService {

    private final WeddingChannelRepository weddingChannelRepository;
    private final WeddingRepository weddingRepository;
    private final ProfileRepository profileRepository;
    private final LikesRepository likesRepository;

    public List<Wedding> getWeddingParticipateList(Long userId) {
        Profile profile = profileRepository.findByUserIdByQuerydsl(userId)
                .orElseThrow(EntityNotFoundException::new);

        return weddingChannelRepository.findAllByProfile(profile).stream()
                .map(WeddingChannel::getWedding)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteExpiredWeddingsAsOfToday() {  // wedding 은 weddingDate 일자가 지나면 제거된다.
        weddingRepository.deleteWeddingsByWeddingDateIsBefore(LocalDate.now());
    }

    @Transactional
    public Wedding createWedding(Wedding wedding) {
        while (true) {
            String newCode = createWeddingCode();
            //validation
            if (!weddingRepository.existsByWeddingCode(newCode)) {
                wedding.applyWeddingCode(newCode);
                break;
            }
        }

        return weddingRepository.save(wedding);
    }

    public String createWeddingCode() {
        String generatedCode = RandomStringUtils.randomAlphanumeric(6);
        return generatedCode.toUpperCase();
    }

    public List<Profile> getMyLikesProfile(final Long profileId, final Long weddingId) {
        return likesRepository.findAllBySenderIdAndIsMatch(profileId, weddingId, FALSE).stream()
                .map(Likes::getReceiver)
                .collect(Collectors.toList());
    }

    public Map<Profile, String> getMyMatchingProfiles(final Long profileId, final Long weddingId) {
        return likesRepository.findMatchingProfileWithMessage(profileId, weddingId, TRUE).stream()
                .collect(Collectors.toMap(Likes::getSender, Likes::getMessage));
    }
}
