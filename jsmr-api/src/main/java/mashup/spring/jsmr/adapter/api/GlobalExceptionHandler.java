package mashup.spring.jsmr.adapter.api;

import lombok.extern.slf4j.Slf4j;
import mashup.spring.jsmr.domain.exception.BusinessException;
import mashup.spring.jsmr.domain.exception.ExceptionCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ExceptionResponse response = ExceptionResponse.of(ExceptionCode.INVALID_INPUT_VALUE);
        return new ResponseEntity<>(response, ExceptionCode.INVALID_INPUT_VALUE.getStatus());
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ExceptionResponse response = ExceptionResponse.of(ExceptionCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, ExceptionCode.METHOD_NOT_ALLOWED.getStatus());
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handleBusinessException(final BusinessException e) {
        log.error("BusinessException ", e);
        final ExceptionCode errorCode = e.getErrorCode();
        final ExceptionResponse response = ExceptionResponse.of(errorCode);
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    /**
     * 예상치 못한 에러를 잡기 위해 Exception handler 정의
     * return 500 Error
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionResponse> handleException(Exception e) {
        log.error("Exception (Unexpected) ", e);
        ExceptionResponse response = ExceptionResponse.of(ExceptionCode.INTERNAL_SERVER_ERROR, e.getMessage());
        return new ResponseEntity<>(response, ExceptionCode.INTERNAL_SERVER_ERROR.getStatus());
    }
}
