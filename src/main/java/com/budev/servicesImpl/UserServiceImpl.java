package com.budev.servicesImpl;

import com.budev.dto.ResetPasswordDto;
import com.budev.dto.UserDto;
import com.budev.entities.Authority;
import com.budev.entities.User;
import com.budev.repositories.AuthorityRepository;
import com.budev.repositories.UserRepository;
import com.budev.services.AuthorityService;
import com.budev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public User findByUsername(String authUsername) {
        return userRepository.findByUsername(authUsername);
    }

    @Override
    public void save(User adminUser) {
        userRepository.save(adminUser);
    }

    @Override
    public Optional<User> findById(int userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void update(UserDto userDto, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        user.setId(user.getId());
        user.setFirst_name(userDto.getFirst_name());
        user.setLast_name(userDto.getLast_name());
        user.setEmail(userDto.getEmail());
        user.setCell_phone(userDto.getCell_phone());
        user.setDepartment(userDto.getDepartment());
        user.setDesignation(userDto.getDesignation());
        user.setHighest_education_level(userDto.getHighest_education_level());
        userRepository.save(user);
    }

    @Override
    public List<User> getAlluser() {
        return userRepository.findAll();
    }

    @Override
    public void createUser(UserDto userDto) {
        User newUser  = new User();
        newUser.setFirst_name(userDto.getFirst_name());
        newUser.setLast_name(userDto.getLast_name());
        newUser.setUsername("@"+userDto.getLast_name().substring(0,3)+new Random().nextInt(1000));
        newUser.setDesignation(userDto.getDesignation());
        newUser.setDepartment(userDto.getDepartment());
        newUser.setEmail(userDto.getEmail());
        newUser.setCell_phone(userDto.getCell_phone());
        newUser.setHighest_education_level(userDto.getHighest_education_level());


        Optional<Authority> authority = authorityRepository.findById(userDto.getAuthorityId());

        Set<Authority> default_authorities = new HashSet<>();
        default_authorities.add(authority.get());

        newUser.setAuthorities(default_authorities);

        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

        userRepository.save(newUser);
    }

    @Override
    public void removeUserById(int userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void resetPassword(User user) {
        userRepository.save(user);
    }


    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Authority> roles){
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority()))
                .collect(Collectors.toList());
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username or password!");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getAuthorities()));

    }
}
