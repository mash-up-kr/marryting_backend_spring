package mashup.spring.jsmr.adapter.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;
import mashup.spring.jsmr.domain.profile.Profile;

import java.time.LocalDate;

@UtilityClass
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AgeUtil {
    public static int CalculateUserAge(String birth) {
        return LocalDate.now().getYear() - Integer.parseInt(birth.substring(0,4)) + 1;
    }
}
