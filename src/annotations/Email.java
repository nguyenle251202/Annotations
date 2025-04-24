// Kiem tra chuooix email hop le
package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;

@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {TYPE_USE, METHOD, FIELD, PARAMETER,})
public @interface Email {
    String message() default "Email is not valid";

    String regexp() default "[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}";
}
