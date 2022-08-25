package mashup.spring.jsmr.domain.exception;

public class SocialLoginException extends BusinessException {

    public SocialLoginException() {
        super(ExceptionCode.SOCIAL_LOGIN_FAIL);
    }
}
