package com.softuni.parking.services;

import com.softuni.parking.domain.entities.UserRoleEntity;
import com.softuni.parking.domain.enums.UserRole;
import com.softuni.parking.repositories.UserRoleEntityRepository;
import org.springframework.stereotype.Service;

@Service
public class UserRoleEntityService {
    private final UserRoleEntityRepository userRoleEntityRepository;

    public UserRoleEntityService(UserRoleEntityRepository userRoleEntityRepository) {
        this.userRoleEntityRepository = userRoleEntityRepository;
    }

    public UserRoleEntity findByRole(UserRole role) {
        return this.userRoleEntityRepository.findFirstByRole(role).orElse(null);
    }

    public void saveRole(UserRoleEntity userRoleEntity) {
        this.userRoleEntityRepository.saveAndFlush(userRoleEntity);
    }

    public Long getCount() {
        return this.userRoleEntityRepository.count();
    }
}
