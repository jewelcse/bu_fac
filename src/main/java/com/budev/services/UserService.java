package com.budev.services;

import com.budev.dto.ResetPasswordDto;
import com.budev.dto.UserDto;
import com.budev.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface UserService  extends UserDetailsService {
    
    User findByUsername(String username);

    void save(User adminUser);

    Optional<User> findById(int userId);

    void update(UserDto user, Principal principal);

    List<User> getAlluser();

    void createUser(UserDto userDto);

    void removeUserById(int userId);

    void resetPassword(User user);
}
