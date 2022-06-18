package mashup.spring.jsmr.adapter.infrastructure.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@ConfigurationProperties(prefix = "jwt")
@Configuration
public class JwtProperty {

    private String accessTokenSecretKey;
    private Long accessTokenValidTime;
}

