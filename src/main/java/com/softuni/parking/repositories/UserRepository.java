package com.softuni.parking.repositories;

import com.softuni.parking.domain.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findFirstByUsername(String username);

    Optional<UserEntity> findFirstByEmail(String email);
}
