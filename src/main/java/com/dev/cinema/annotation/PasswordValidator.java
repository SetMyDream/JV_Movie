package com.dev.cinema.annotation;

import com.dev.cinema.dto.UserRequestDto;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordValidator implements
        ConstraintValidator<PasswordConstraint, UserRequestDto> {
    @Override
    public boolean isValid(UserRequestDto userRequestDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRequestDto.getPassword() != null && userRequestDto.getPassword()
                .equals(userRequestDto.getRepeatPassword());
    }
}
