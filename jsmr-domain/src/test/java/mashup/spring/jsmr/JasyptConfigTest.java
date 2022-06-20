package mashup.spring.jsmr;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import mashup.spring.jsmr.config.JasyptConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.test.context.ContextConfiguration;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@ContextConfiguration(classes = {
    JasyptConfig.class
})
@SpringBootTest
public class JasyptConfigTest {

    @Value("${jasypt.encryptor.password}")
    private String jasyptEncryptorPassword;

    @Autowired
    private ConfigurableEnvironment configurableEnvironment;

    private DefaultLazyEncryptor encryptor;

    @BeforeEach
    void setUp() throws Exception {
        System.out.println(jasyptEncryptorPassword);
        if(StringUtils.isBlank(jasyptEncryptorPassword)) {
            throw new Exception("jasypt.encryptor.password must not be null, empty or blank.");
        }
        encryptor = new DefaultLazyEncryptor(configurableEnvironment);
    }

    @ParameterizedTest
    @MethodSource("stringForEncrypt")
    void encryptTest(String source) {
        System.out.println("source: " + source);
        System.out.println("encrypted: " + encryptor.encrypt(source));
        assertThat(true).isTrue();
    }

    private static Stream<Arguments> stringForEncrypt() { // argument source method
        return Stream.of(
                Arguments.of("Test!")
        );
    }
}
