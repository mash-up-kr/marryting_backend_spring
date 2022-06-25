package mashup.spring.jsmr.adapter.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class ApiResponse<T> {

    private int status;
    private T data;

    public static <T> ApiResponse<T> success(HttpStatus httpStatus) {
        return new ApiResponse<>(httpStatus.value(), null);
    }

    public static <T> ApiResponse<T> success(HttpStatus httpStatus, T data) {
        return new ApiResponse<>(httpStatus.value(), data);
    }

}
