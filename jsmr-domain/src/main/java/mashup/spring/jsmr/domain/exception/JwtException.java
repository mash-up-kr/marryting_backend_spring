package mashup.spring.jsmr.domain.exception;

public class JwtException extends BusinessException {

    public JwtException() {
        super(ExceptionCode.JWT_EXCEPTION);
    }
}
