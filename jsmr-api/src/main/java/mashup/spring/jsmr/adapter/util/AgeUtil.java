package mashup.spring.jsmr.adapter.util;

import mashup.spring.jsmr.domain.profile.Profile;

import java.time.LocalDate;

public class AgeUtil {
    public static int CalculateUserAge(String birth) {
        return LocalDate.now().getYear() - Integer.parseInt(birth.substring(0,4)) + 1;
    }
}
