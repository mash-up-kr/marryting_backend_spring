package mashup.spring.jsmr.adapter.infrastructure.interceptor;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LoginUserId {
}
