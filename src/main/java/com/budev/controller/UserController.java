package com.budev.controller;


import com.budev.dto.PublicationDto;
import com.budev.dto.ResetPasswordDto;
import com.budev.dto.UserDto;
import com.budev.dto.UserResponseDto;
import com.budev.entities.Authority;
import com.budev.entities.User;
import com.budev.services.AuthorityService;
import com.budev.services.PublicationService;
import com.budev.services.ResearchService;
import com.budev.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private UserService userService;
    private PublicationService publicationService;
    private ResearchService researchService;
    private AuthorityService authorityService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService,PublicationService publicationService,AuthorityService authorityService,ResearchService researchService){
        this.userService = userService;
        this.publicationService = publicationService;
        this.authorityService = authorityService;
        this.researchService = researchService;
    }

    @GetMapping("/users")
    public String getUsers(Model model) {

        List<User> users = userService.getAlluser();

        List<UserResponseDto> results = users.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

        model.addAttribute("users",results);
        return "users/users";
    }

    private UserResponseDto convertToDto(User user) {
        UserResponseDto userResponseDto = modelMapper.map(user, UserResponseDto.class);
        userResponseDto.setAuthorities(new ArrayList<>(user.getAuthorities()));

        return userResponseDto;
    }


    @GetMapping("/add-new-user")
    public String createUser(Model model) {

        List<Authority> authorities = authorityService.findAllAuthority();

        model.addAttribute("user",new UserDto());
        model.addAttribute("authorities",authorities);

        return "users/userAddForm";
    }

    @PostMapping("/save-user")
    public String saveUser(@ModelAttribute("user") UserDto userDto) {
        userService.createUser(userDto);
        return "redirect:/add-new-user?success";
    }

    @GetMapping("/user-remove/{userId}")
    public String removeUser(@PathVariable("userId") int userId){
        userService.removeUserById(userId);
        return "redirect:/users?success";
    }

    @GetMapping("/reset-password")
    public String resetPassword(Model model){
        model.addAttribute("user",new ResetPasswordDto());
        return "profile/resetPassword";
    }
//    $2a$10$EqGiJT7OdeJyhtddtoxNNOSEqLhNGb3bJ.Fp.95OF7o.qZC8MW2wK
//12345
//    $2a$10$lQe1Jq8eTf.w4v3U/KLfpuJKOTQTD.VMWymMEr0PjgoO6Zw/apMJq
    @PostMapping("/update-password")
    public String updatePassword(@ModelAttribute("user") ResetPasswordDto resetPasswordDto, Principal principal){
        User user = userService.findByUsername(principal.getName());

        if (!passwordEncoder.matches(resetPasswordDto.getCurrentPassword(),user.getPassword())){
            return "redirect:/reset-password?wrongCurrentPass";
        }
        if (!resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword())){
            return "redirect:/reset-password?mismatchpass";
        }


//        if (!user.getPassword().equals(passwordEncoder.encode(resetPasswordDto.getCurrentPassword()))){
//            return "redirect:/reset-password?wrongCurrentPass";
//        }
//        if (!resetPasswordDto.getNewPassword().equals(resetPasswordDto.getConfirmPassword())){
//            return "redirect:/reset-password?mismatchpass";
//        }
        user.setPassword(passwordEncoder.encode(resetPasswordDto.getNewPassword()));
        userService.resetPassword(user);
        return "redirect:/profile?updatePass";
    }


}
