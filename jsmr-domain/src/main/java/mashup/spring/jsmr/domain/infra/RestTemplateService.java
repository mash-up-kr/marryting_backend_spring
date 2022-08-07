package mashup.spring.jsmr.domain.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class RestTemplateService {

    private final RestTemplate restTemplate;

    public <T> ResponseEntity<T> get(String url, HttpHeaders headers, Class<T> clazz) {
        return call(url, HttpMethod.GET, headers, null, clazz);
    }

    public <T> ResponseEntity<T> post(String url, HttpHeaders headers, T body, Class<T> clazz) {
        return call(url, HttpMethod.POST, headers, body, clazz);
    }

    private <T> ResponseEntity<T> call(String url, HttpMethod method, HttpHeaders headers, T body, Class<T> clazz) {
        return restTemplate.exchange(url, method, new HttpEntity<>(body, headers), clazz);
    }
}
