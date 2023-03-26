package com.softuni.parking.domain.dto;

import com.softuni.parking.Validation.MatchingPasswordValidation.MatchingPasswords;
import com.softuni.parking.Validation.UniqueEmailValidation.UniqueEmail;
import com.softuni.parking.Validation.UniqueUsernameValidation.UniqueUsername;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@NoArgsConstructor
@Getter
@Setter
@MatchingPasswords
public class UserRegisterDto {
    @UniqueUsername
    @NotBlank(message = "Please enter a username")
    @Length(min = 3, max = 20)
    String username;

    @Length(min = 2, max = 20)
    @NotBlank
    String firstName;

    @Length(min = 2, max = 20)
    @NotBlank
    String lastName;

    @UniqueEmail
    @NotBlank
    String email;

    @Length(min = 3, max = 20)
    String password;

    @Length(min = 3, max = 20)
    String confirmPassword;

}
