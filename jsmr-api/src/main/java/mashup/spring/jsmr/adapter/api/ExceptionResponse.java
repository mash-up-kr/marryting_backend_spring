package mashup.spring.jsmr.adapter.api;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mashup.spring.jsmr.exception.ExceptionCode;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExceptionResponse {

    private String message;
    private int status;
    private String code;

    private ExceptionResponse(final ExceptionCode code) {
        this.message = code.getMessage();
        this.status = code.getStatus().value();
        this.code = code.getCode();
    }

    private ExceptionResponse(String message, int status, String code) {
        this.message = message;
        this.status = status;
        this.code = code;
    }

    public static ExceptionResponse of(final ExceptionCode code) {
        return new ExceptionResponse(code);
    }

    public static ExceptionResponse of(final ExceptionCode code, final String additionalMessage) {
        String message = String.format("%s - %s", code.getMessage(), additionalMessage);
        return new ExceptionResponse(message, code.getStatus().value(), code.getCode());
    }
}

