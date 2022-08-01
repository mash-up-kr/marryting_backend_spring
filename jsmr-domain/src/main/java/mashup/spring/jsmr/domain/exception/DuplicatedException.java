package mashup.spring.jsmr.domain.exception;

public class DuplicatedException extends BusinessException{
    public DuplicatedException() { super(ExceptionCode.EXIST_ENTITY); }
}
