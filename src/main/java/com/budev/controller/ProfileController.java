package com.budev.controller;


import com.budev.dto.PublicationDto;
import com.budev.dto.UserDto;
import com.budev.entities.Publication;
import com.budev.entities.Research;
import com.budev.entities.User;
import com.budev.services.PublicationService;
import com.budev.services.ResearchService;
import com.budev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfileController {

    private UserService userService;
    private PublicationService publicationService;
    private ResearchService researchService;

    @Autowired
    public ProfileController(UserService userService,PublicationService publicationService,ResearchService researchService){
        this.userService = userService;
        this.publicationService = publicationService;
        this.researchService = researchService;
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {

        User user = userService.findByUsername(principal.getName());
        List<Publication> publications = publicationService.findAllPublicationByUserId(user.getId());
        List<Research> researches = researchService.findAllResearchByUserId(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("publications",publications);
        model.addAttribute("researches",researches);

        return "profile/profile";

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") UserDto user, Principal principal){
        System.out.println("update profile");
        userService.update(user,principal);
        return "redirect:/profile?success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/edit-profile")
    public String editProfile(Model model,Principal principal){

        User user = userService.findByUsername(principal.getName());

        model.addAttribute("user",user);

        return "profile/editProfile";
    }




}
