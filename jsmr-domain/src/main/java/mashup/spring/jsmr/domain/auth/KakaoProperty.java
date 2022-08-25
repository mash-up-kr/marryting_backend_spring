package mashup.spring.jsmr.domain.auth;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Setter
@Getter
@ConfigurationProperties(prefix = "kakao")
public class KakaoProperty {

    private String profileUrl;

}
