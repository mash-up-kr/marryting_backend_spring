package mashup.spring.jsmr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @EnableScheduling  추후 배포 이전에 주석 해제
@SpringBootApplication
public class JsmrApplication {

    public static void main(String[] args) {
        SpringApplication.run(JsmrApplication.class, args);
    }

}
