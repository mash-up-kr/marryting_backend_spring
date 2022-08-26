package mashup.spring.jsmr.domain.wedding;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@SpringBootTest
class WeddingServiceTest {

    @Autowired
    private WeddingService weddingService;

    @Autowired
    private WeddingRepository weddingRepository;

    @Test
    void 기간_민료_웨딩_삭제() {
        Wedding wedding = Wedding.builder()
                .weddingDate(LocalDate.of(2022, 7, 25))
                .groomName("김팔봉")
                .brideName("팔봉김")
                .build();
        Wedding wedding1 = weddingService.createWedding(wedding);
        String weddingCode = wedding1.getWeddingCode();

        boolean before = weddingRepository.existsByWeddingCode(weddingCode);
        assertThat(before).isEqualTo(true);

        weddingService.deleteExpiredWeddingsAsOfToday();

        boolean after = weddingRepository.existsByWeddingCode(weddingCode);
        assertThat(after).isEqualTo(false);
    }
}