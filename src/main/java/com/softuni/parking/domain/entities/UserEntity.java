package com.softuni.parking.domain.entities;

import com.softuni.parking.Validation.UniqueEmailValidation.UniqueEmail;
import com.softuni.parking.Validation.UniqueUsernameValidation.UniqueUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {
    @NotBlank
    @Column(unique = true, nullable = false)
    @Length(min = 3, max = 20)
    String username;

    @Column(name = "first_name", nullable = false)
    @Length(min = 2, max = 20)
    String firstName;

    @Column(name = "last_name", nullable = false)
    @Length(min = 2, max = 20)
    String lastName;

    @Email
    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    @Length(min = 3)
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    List<UserRoleEntity> roles;

}
