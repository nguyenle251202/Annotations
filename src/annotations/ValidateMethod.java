// Kiem tra tinh hop le cua dau vao
package annotations;

public @interface ValidateMethod {
    String message() default("Invalid input string, please check again");
}
