package com.budev.controller;


import com.budev.dto.AuthorityDto;
import com.budev.dto.PublicationDto;
import com.budev.dto.ResearchDto;
import com.budev.entities.Authority;
import com.budev.entities.Publication;
import com.budev.entities.Research;
import com.budev.entities.User;
import com.budev.services.AuthorityService;
import com.budev.services.PublicationService;
import com.budev.services.ResearchService;
import com.budev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.security.Principal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
public class HomeController {

    private UserService userService;
    private PublicationService publicationService;
    private ResearchService researchService;
    private AuthorityService authorityService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public HomeController(UserService userService,PublicationService publicationService,AuthorityService authorityService,ResearchService researchService){
        this.userService = userService;
        this.publicationService = publicationService;
        this.authorityService = authorityService;
        this.researchService = researchService;
    }

    @GetMapping("/")
    public String homePage(){
        return "index";
    }


    /*
    Publication  manages methods
     */

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/add-new-publication")
    public String createNewPublication(Model model) {
        model.addAttribute("publication",new PublicationDto());
        return "publications/publicationAddForm";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/save-new-publication")
    public String saveNewPublication(@Valid @ModelAttribute("publication") PublicationDto publication, BindingResult bindingResult,  Principal principal) {

        if(bindingResult.hasErrors()) {
            return "publications/publicationAddForm";
        }

        publicationService.saveNewPublication(publication,principal);
        return "redirect:/add-new-publication?success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/edit-publication/{id}")
    public String editPublication(Model model,@PathVariable("id") int publicationId){

        Optional<Publication> publication = publicationService.getPublicationById(publicationId);
        if (publication.isEmpty()){
            return "error";
        }

        model.addAttribute("publication",publication.get());

        return "publications/publicationEditForm";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update-publication/{id}")
    public String updatePublication(@ModelAttribute("publication")PublicationDto publicationDto, @PathVariable("id") int publicationId){
        publicationService.update(publicationDto, publicationId);
        return "redirect:/profile?success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/remove-publication/{id}")
    public String removePublication(@PathVariable("id") int publicationId){
        publicationService.remove(publicationId);
        return "redirect:/profile?remove";
    }

    /*
    Research Manages Methods
     */

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/add-new-research")
    public String createNewResearch(Model model) {
        model.addAttribute("research",new ResearchDto());
        return "researches/researchAddForm";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/save-new-research")
    public String saveNewResearch(@Valid @ModelAttribute("research") ResearchDto research, BindingResult bindingResult, Principal principal) {

        if(bindingResult.hasErrors()) {
            return "researches/researchAddForm";
        }

        researchService.saveNewResearch(research,principal);
        return "redirect:/add-new-research?success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/edit-research/{id}")
    public String editResearch(Model model,@PathVariable("id") int researchId){

        Optional<Research> research = researchService.getResearchById(researchId);
        if (research.isEmpty()){
            return "error";
        }

        model.addAttribute("research",research.get());

        return "researches/researchEditForm";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/update-research/{id}")
    public String updateResearch(@ModelAttribute("research")ResearchDto researchDto, @PathVariable("id") int researchId){
        researchService.update(researchDto, researchId);
        return "redirect:/profile?success";
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/remove-research/{id}")
    public String removeResearch(@PathVariable("id") int researchId){
        researchService.remove(researchId);
        return "redirect:/profile?remove";
    }


    /*
    Authority manages methods
     */

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add-new-role")
    public String createNewRole(Model model){
        model.addAttribute("role",new AuthorityDto());
        return "role/roleAddForm";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/save-new-role")
    public String saveNewRole(@ModelAttribute("role") AuthorityDto authorityDto){


        Optional<Authority> authority = authorityService.findByAuthority(authorityDto.getAuthorityName().toUpperCase());

        if (authority.isEmpty()){
            authorityService.createNewRole(authorityDto);
            return "redirect:/add-new-role?success";
        }
        else  {
            return "redirect:/add-new-role?duplicate";
        }

    }

    /*
    Init data for first time only
     */


    //@PostConstruct
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    //@GetMapping("/init")
    public void initRoleAndUser() {

        Authority adminRole = new Authority();
        adminRole.setAuthority("ADMIN");
        authorityService.save(adminRole);



        User adminUser = new User();
        adminUser.setFirst_name("Jewel");
        adminUser.setLast_name("chowdhury");
        adminUser.setUsername("admin");
        adminUser.setEmail("admin@gmail.com");
        adminUser.setPassword(passwordEncoder.encode("admin123"));

        Set<Authority> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);

        adminUser.setAuthorities(adminRoles);

        userService.save(adminUser);

    }
}
