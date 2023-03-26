package com.softuni.parking.domain.dto;

import com.softuni.parking.Validation.UniqueUsernameValidation.UniqueUsername;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
@Getter
@Setter
@NoArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "Please enter a username")
    @Length(min = 3, max = 20)
    String username;

    @Length(min = 3, max = 20)
    @NotBlank(message = "Please enter a password")
    String password;
}
