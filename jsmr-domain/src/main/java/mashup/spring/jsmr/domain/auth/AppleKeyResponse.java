package mashup.spring.jsmr.domain.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleKeyResponse {
    private final List<AppleKey> keys = Collections.emptyList();
}