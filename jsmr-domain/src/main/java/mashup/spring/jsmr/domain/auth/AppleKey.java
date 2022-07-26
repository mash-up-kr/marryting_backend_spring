package mashup.spring.jsmr.domain.auth;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AppleKey {
    private final String kty = "";
    private final String kid = "";
    private final String use = "";
    private final String alg = "";
    private final String n = "";
    private final String e = "";
}