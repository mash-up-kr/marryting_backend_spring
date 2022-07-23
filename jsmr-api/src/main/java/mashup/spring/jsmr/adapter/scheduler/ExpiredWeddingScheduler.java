package mashup.spring.jsmr.adapter.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.application.WeddingApplicationService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ExpiredWeddingScheduler {

    private final WeddingApplicationService weddingApplicationService;

    @Scheduled(cron = "0 0 3 * * *") // 매일 새벽 03:00 scheduling
    @Async("asyncExecutor")
    public void deleteExpiredWedding(){
        long start = System.currentTimeMillis();
        weddingApplicationService.deleteExpiredWeddings();
        long end = System.currentTimeMillis();
        log.info("Scheduled Expired Weddings - START : {} \n" +
                 "END : {} \n" +
                 "Total Working Time : {}", start, end, end-start);
    }
}
