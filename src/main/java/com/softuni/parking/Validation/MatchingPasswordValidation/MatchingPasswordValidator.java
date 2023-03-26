package com.softuni.parking.Validation.MatchingPasswordValidation;

import com.softuni.parking.domain.dto.UserRegisterDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MatchingPasswordValidator implements ConstraintValidator<MatchingPasswords, Object> {
    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        UserRegisterDto userRegisterDto = (UserRegisterDto) object;
        return userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword());
    }

    @Override
    public void initialize(MatchingPasswords constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
