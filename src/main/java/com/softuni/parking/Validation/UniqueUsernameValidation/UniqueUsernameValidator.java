package com.softuni.parking.Validation.UniqueUsernameValidation;

import com.softuni.parking.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserService userService;

    public UniqueUsernameValidator(UserService userService) {
        this.userService = userService;
    }


    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return this.userService.findUserByUserName(username) == null;
    }

    @Override
    public void initialize(UniqueUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
