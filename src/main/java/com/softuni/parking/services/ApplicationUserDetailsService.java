package com.softuni.parking.services;

import com.softuni.parking.domain.entities.UserEntity;
import com.softuni.parking.repositories.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.softuni.parking.domain.utils.Constants.USER_NOT_FOUND_ERROR;
@Service
public class ApplicationUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public ApplicationUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserEntity userEntity = this.userService.findUserByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_ERROR, username)));

        return new User(userEntity.getUsername(), userEntity.getPassword(), userEntity.getRoles());
    }
}
