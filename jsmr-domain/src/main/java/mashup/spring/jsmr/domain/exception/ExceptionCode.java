package mashup.spring.jsmr.domain.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ExceptionCode {
    // Global
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "Global001", "Invalid Input Value"),
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "Global002", "Invalid Input Value"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "Global004", "Server Error"),
    INVALID_TYPE_VALUE(HttpStatus.BAD_REQUEST, "Global005", "Invalid Type Value"),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


}
