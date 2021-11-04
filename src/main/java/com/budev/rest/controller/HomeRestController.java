package com.budev.rest.controller;


import com.budev.entities.Publication;
import com.budev.entities.Research;
import com.budev.entities.User;
import com.budev.exceptions.UserNotFoundException;
import com.budev.rest.dto.FacultyProfile;
import com.budev.services.PublicationService;
import com.budev.services.ResearchService;
import com.budev.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
public class HomeRestController {

    @Autowired
    private UserService userService;
    @Autowired
    private PublicationService publicationService;
    @Autowired
    private ResearchService researchService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getFacultyProfiles(){
        List<User> users = userService.getAlluser();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<FacultyProfile> getFacultyProfileById(@PathVariable("userId") int userId) throws UserNotFoundException {
        Optional<User> user = userService.findById(userId);
        if (user.isEmpty()){
            throw  new UserNotFoundException("User Not Found for Id "+userId);
        }
        List<Publication> publications = publicationService.findAllPublicationByUserId(userId);
        List<Research> researches = researchService.findAllResearchByUserId(userId);

        FacultyProfile profile = new FacultyProfile();
        profile.setUser(user.get());
        profile.setPublications(publications);
        profile.setResearches(researches);

        return new ResponseEntity<>(profile, HttpStatus.OK);
    }



}
