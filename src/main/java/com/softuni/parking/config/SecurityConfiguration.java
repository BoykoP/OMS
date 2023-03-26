package com.softuni.parking.config;

import com.softuni.parking.repositories.UserRepository;
import com.softuni.parking.services.ApplicationUserDetailsService;
import com.softuni.parking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http,
                                           SecurityContextRepository securityContextRepository) throws Exception {

        http
                .authorizeHttpRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                .permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/users/login", "/users/register").anonymous()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/users/login")
                .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                .defaultSuccessUrl("/home")
//                .failureForwardUrl("/users/login-error")
                .and().logout()
                .logoutUrl("/users/logout")
                .logoutSuccessUrl("/")
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .and().securityContext().securityContextRepository(securityContextRepository);



        return http.build();
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http,
//                                           SecurityContextRepository securityContextRepository) throws Exception {
//        http.
//                // defines which pages will be authorized
//                        authorizeHttpRequests().
//                // allow access to all static files (images, CSS, js)
//                        requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
//                // the URL-s below are available for all users - logged in and anonymous
//                        requestMatchers("/", "/users/login", "/users/register", "/users/login-error").permitAll().
//                anyRequest().authenticated().
//                and().
//                // configure login with HTML form
//                        formLogin().
//                loginPage("/users/login").
//                // the names of the username, password input fields in the custom login form
//                        usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY).
//                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
//                // where do we go after login
//                        defaultSuccessUrl("/").//use true argument if you always want to go there, otherwise go to previous page
//                failureForwardUrl("/users/login-error").
//                and().logout().//configure logout
//                logoutUrl("/users/logout").
//                logoutSuccessUrl("/").//go to homepage after logout
//                invalidateHttpSession(true).
//                and().
//                securityContext().
//                securityContextRepository(securityContextRepository);
//
//        return http.build();
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserService userService) {
        return new ApplicationUserDetailsService(userService);
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService(userService));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new DelegatingSecurityContextRepository(
                new RequestAttributeSecurityContextRepository(),
                new HttpSessionSecurityContextRepository()
        );
    }


}
