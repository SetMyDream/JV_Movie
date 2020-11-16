package com.dev.cinema.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.routines.EmailValidator;

public class MailValidator implements ConstraintValidator<EmailConstraint, String> {
    @Override
    public boolean isValid(String email,
                           ConstraintValidatorContext constraintValidatorContext) {
        return email != null && email.length() > 8
                && email.length() < 50
                && EmailValidator
                .getInstance().isValid(email);
    }
}
