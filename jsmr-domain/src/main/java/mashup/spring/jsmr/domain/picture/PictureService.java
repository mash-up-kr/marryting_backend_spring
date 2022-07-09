package mashup.spring.jsmr.domain.picture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class PictureService {
    private PictureRepository pictureRepository;

    public void saveAll(List<Picture> pictures) {
        pictureRepository.saveAll(pictures);
    }
}
