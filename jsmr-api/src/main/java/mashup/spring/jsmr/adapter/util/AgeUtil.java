package mashup.spring.jsmr.adapter.util;
import lombok.experimental.UtilityClass;
import java.time.LocalDate;

@UtilityClass
public class AgeUtil {
    public static int CalculateUserAge(String birth) {
        return LocalDate.now().getYear() - Integer.parseInt(birth.substring(0,4)) + 1;
    }
}
