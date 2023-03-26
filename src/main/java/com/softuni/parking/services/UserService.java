package com.softuni.parking.services;

import com.softuni.parking.domain.dto.UserRegisterDto;
import com.softuni.parking.domain.entities.UserEntity;
import com.softuni.parking.domain.entities.UserRoleEntity;
import com.softuni.parking.domain.enums.UserRole;
import com.softuni.parking.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final UserRoleEntityService userRoleEntityService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, ModelMapper modelMapper, UserRoleEntityService userRoleEntityService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.userRoleEntityService = userRoleEntityService;
        this.passwordEncoder = passwordEncoder;
    }

    public void createUser(UserEntity user) {
        this.userRepository.saveAndFlush(user);
    }

    public void createUser(UserRegisterDto userRegisterDto) {
        UserEntity userEntity = this.modelMapper.map(userRegisterDto, UserEntity.class);
        List<UserRoleEntity> roles = List.of(this.userRoleEntityService.findByRole(UserRole.USER));
        userEntity.setRoles(roles);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        String debug = "";
        this.userRepository.saveAndFlush(userEntity);

    }

    public Optional<UserEntity> findUserByUserName(String username){
        return this.userRepository.findFirstByUsername(username);
    }

    public UserEntity findUserByEmail(String email){
        return this.userRepository.findFirstByEmail(email).orElse(null);
    }

}
