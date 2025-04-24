package core;

import annotations.*;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class Validator {
    public static String validator(Object obj) {
        Class<?> cla = obj.getClass();
        Field[] fields = cla.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);

            // NotNull
            if (field.isAnnotationPresent(NotNull.class)) {
                try {
                    Object value = field.get(obj);
                    if (value == null || (value instanceof String && ((String) value).trim().isEmpty())) {
                        System.out.println("Field '" + field.getName() + "' must NOT be null!");
                    } else {
                        System.out.println("Field '" + field.getName() + "' is valid");
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("Cannot access field: " + field.getName());
                }
            }

            // Email
            if (field.isAnnotationPresent(Email.class)) {
                Email emailAnnotation = field.getAnnotation(Email.class);
                String message = emailAnnotation.message();
                String regex = emailAnnotation.regexp();
                try {
                    Object value = field.get(obj);
                    if (value instanceof String strValue) {
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(strValue);

                        if (!matcher.matches()) {
                            System.out.println("Field '" + field.getName() + "' regex not match: " + regex);
                        } else {
                            System.out.println("Field '" + field.getName() + "' valid.");
                        }
                    }
                } catch (IllegalAccessException | PatternSyntaxException e) {
                    e.printStackTrace();
                }
            }

            // Future
            if (field.isAnnotationPresent(Future.class)) {
                Future futureAnnotation = field.getAnnotation(Future.class);
                String message = futureAnnotation.message();
                String regex = futureAnnotation.regexp();
                try {
                    Object value = field.get(obj);
                    if (value instanceof String strValue) {
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(strValue);
                        if (!matcher.matches()) {
                            System.out.println("Field '" + field.getName() + "' regex not match");
                        } else {
                            if (LocalDate.parse(strValue).isAfter(LocalDate.now())) {
                                System.out.println("Field '" + field.getName() + "' is in the future");
                            }
                        }
                    }
                } catch (IllegalAccessException | PatternSyntaxException e) {
                    System.out.println("Cannot access field: " + field.getName());
                }
            }

            // Past
            if (field.isAnnotationPresent(Past.class)) {
                Past pastAnnotation = field.getAnnotation(Past.class);
                String message = pastAnnotation.message();
                String regex = pastAnnotation.regexp();
                try {
                    Object value = field.get(obj);
                    if (value instanceof String strValue) {
                        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
                        Matcher matcher = pattern.matcher(strValue);
                        if (!matcher.matches()) {
                            System.out.println("Field '" + field.getName() + "' regex not match");
                        } else {
                            if (LocalDate.parse(strValue).isBefore(LocalDate.now())) {
                                System.out.println("Field '" + field.getName() + "' is in the past");
                            } else {
                                System.out.println("Field '" + field.getName() + "' valid.");
                            }
                        }
                    }
                } catch (IllegalAccessException | PatternSyntaxException e) {
                    System.out.println("Cannot access field: " + field.getName());
                }
            }

            // Range
            if (field.isAnnotationPresent(Range.class)) {
                Range rangeAnnotation = field.getAnnotation(Range.class);
                int minAge = rangeAnnotation.minAge();
                int maxAge = rangeAnnotation.maxAge();
                String message = rangeAnnotation.message();
                try {
                    Object value = field.get(obj);
                    if (value instanceof Integer intValue) {
                        if (intValue < minAge || intValue > maxAge) {
                            System.out.println("Field '" + field.getName() + "' Age is not valid");
                        }
                    } else {
                        System.out.println("Field '" + field.getName() + "' is Valid");
                    }
                } catch (Exception e) {
                    System.out.println("Cannot access field: " + field.getName());
                }
            }
        }
        return "asdfasfa";
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private static boolean isValidEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}
