package com.softuni.parking.domain.entities;

import com.softuni.parking.domain.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "user_roles")
@Getter
@Setter
@NoArgsConstructor
public class UserRoleEntity extends BaseEntity implements GrantedAuthority{
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Override
    public String getAuthority() {
        return "ROLE_" + this.role.toString();
    }
}
