package com.softuni.parking.repositories;

import com.softuni.parking.domain.entities.UserRoleEntity;
import com.softuni.parking.domain.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleEntityRepository extends JpaRepository<UserRoleEntity, Long> {
    Optional<UserRoleEntity> findFirstByRole(UserRole role);
}
