package com.dev.cinema.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = EmailValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConstraint {
    String message() default "Email does not match to the standard pattern";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
