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
    ENTITY_NOT_FOUND(HttpStatus.BAD_REQUEST, "Global006", "Entity Not Found"),

    // Security
    FAIL_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "S001", "401 에러 인증 실패"),
    FAIL_AUTHORIZATION(HttpStatus.FORBIDDEN, "S002", "403 에러 권한 없음"),
    JWT_EXCEPTION(HttpStatus.FORBIDDEN, "S003", "JWT에 문제가 있습니다."),

    // Duplicated Entity
    EXIST_ENTITY(HttpStatus.BAD_REQUEST, "D001", "Already Exist Entity");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ExceptionCode(final HttpStatus status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }


}
