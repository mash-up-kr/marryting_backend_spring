package mashup.spring.jsmr.domain.picture;

import lombok.RequiredArgsConstructor;
import mashup.spring.jsmr.domain.profile.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public List<Picture> saveAll(List<String> profileUrls, Profile profile) {
        List<Picture> pictures = new ArrayList<>();
        for (String profileUrl : profileUrls) {
            pictures.add(
                    Picture.builder()
                            .profileUrl(profileUrl)
                            .profile(profile)
                            .build()
            );
        }

        return pictureRepository.saveAll(pictures);
    }
}
