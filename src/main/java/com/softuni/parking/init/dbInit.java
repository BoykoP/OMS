package com.softuni.parking.init;

import com.softuni.parking.domain.entities.UserEntity;
import com.softuni.parking.domain.entities.UserRoleEntity;
import com.softuni.parking.domain.enums.UserRole;
import com.softuni.parking.services.UserRoleEntityService;
import com.softuni.parking.services.UserService;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

import static com.softuni.parking.domain.utils.Constants.*;

@Component
public class dbInit {
    private final UserService userService;
    private final UserRoleEntityService userRoleEntityService;
    private final PasswordEncoder passwordEncoder;

    public dbInit(UserService userService, UserRoleEntityService userRoleEntityService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRoleEntityService = userRoleEntityService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void initRoles() {
        if (this.areRolesCreated()) {
            return;
        }
        UserRoleEntity admin = new UserRoleEntity();
        admin.setRole(UserRole.ADMIN);

        UserRoleEntity user = new UserRoleEntity();
        user.setRole(UserRole.USER);

        this.userRoleEntityService.saveRole(admin);
        this.userRoleEntityService.saveRole(user);
    }

    @PostConstruct
    public void initAdminUser() {
        if (isDefaultAdminCreated()) {
            return;
        }
        UserRoleEntity userRoleEntity = this.userRoleEntityService.findByRole(UserRole.ADMIN);

        UserEntity initialAdmin = new UserEntity();
        initialAdmin.setUsername(INITIAL_ADMIN_USERNAME);
        initialAdmin.setFirstName(INITIAL_ADMIN_FIRST_NAME);
        initialAdmin.setLastName(INITIAL_ADMIN_LAST_NAME);
        initialAdmin.setEmail(INITIAL_ADMIN_EMAIL);
        initialAdmin.setPassword(passwordEncoder.encode(INITIAL_ADMIN_PASSWORD));
        initialAdmin.setRoles(List.of(userRoleEntity));

        this.userService.createUser(initialAdmin);
    }

    public Boolean isDefaultAdminCreated() {
        return this.userService.findUserByUserName(INITIAL_ADMIN_USERNAME) != null;
    }

    public boolean areRolesCreated() {
        return this.userRoleEntityService.getCount() > 0;
    }
}
