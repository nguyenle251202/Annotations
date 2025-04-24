// Gioi han do dai chuoi
package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Length {
    int minLength();

    int maxLength();

    String message() default "10-25 series limit";
}
