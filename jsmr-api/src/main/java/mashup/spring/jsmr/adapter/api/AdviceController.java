package mashup.spring.jsmr.adapter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdviceController {

    @GetMapping("/server-error")
    public void serverError() throws Exception {
        throw new Exception("서버 에러");
    }
}
