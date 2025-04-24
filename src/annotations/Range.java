// Dam bao tuoi trong khoang 0-100
package annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Range {
    int minAge();

    int maxAge();

    String message() default ("Valid age is between 0-100. Please check again.");
}
