// Dam bao ko co null
package annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;

@Documented
@Retention(value= java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value={TYPE_USE, METHOD, FIELD,PARAMETER,})
public @interface NotNull {
    String message() default "Field cannot be null";
}
