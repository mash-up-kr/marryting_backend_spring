package mashup.spring.jsmr;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // 테스트 용 삭제 예정
    @GetMapping("/ping")
    public String test() {
        return "pong";
    }

    @GetMapping("/success")
    public ApiResponse<Void> success() {
        return ApiResponse.success(HttpStatus.OK);
    }

}
