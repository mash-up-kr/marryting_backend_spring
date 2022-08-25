package mashup.spring.jsmr.domain.auth;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@EnableConfigurationProperties(KakaoProperty.class)
@Configuration
public class KakaoConfiguration {

    @Bean
    public WebClient kaKaoWebClient(KakaoProperty kaKaoProperty) {
        return WebClient.builder()
                .baseUrl(kaKaoProperty.getProfileUrl())
                .build();
    }
}


