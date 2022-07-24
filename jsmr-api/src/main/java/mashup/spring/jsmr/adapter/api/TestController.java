package mashup.spring.jsmr.adapter.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/test")
@RestController
public class TestController {

    @GetMapping
    public String test() {
        return "Gyunny CI/CD Hello ㅎㅎㅎㅎㅎ!!!!";
    }
}
