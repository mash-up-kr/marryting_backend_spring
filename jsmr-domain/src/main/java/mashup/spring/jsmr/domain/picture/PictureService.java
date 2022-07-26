package mashup.spring.jsmr.domain.picture;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PictureService {
    private final PictureRepository pictureRepository;

    @Transactional
    public List<Picture> saveAll(List<String> profileUrls, Profile profile) {
        List<Picture> pictures = new ArrayList<>();

        for (String profileUrl : profileUrls) {
            Picture picture = Picture.builder()
                    .profileUrl(profileUrl)
                    .profile(profile)
                    .build();
            pictures.add(picture);
        }

        return pictureRepository.saveAll(pictures);
    }
}