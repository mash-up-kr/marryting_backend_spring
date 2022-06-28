package mashup.spring.jsmr.domain.exception;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException() {
        super(ExceptionCode.ENTITY_NOT_FOUND);
    }
}
