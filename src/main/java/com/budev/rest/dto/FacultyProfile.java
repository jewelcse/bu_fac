package com.budev.rest.dto;


import com.budev.entities.Publication;
import com.budev.entities.Research;
import com.budev.entities.User;

import java.util.List;

public class FacultyProfile {

    private User user;
    private List<Publication> publications;
    private List<Research> researches;

    public FacultyProfile() {
    }

    public FacultyProfile(User user, List<Publication> publications, List<Research> researches) {
        this.user = user;
        this.publications = publications;
        this.researches = researches;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public void setPublications(List<Publication> publications) {
        this.publications = publications;
    }

    public List<Research> getResearches() {
        return researches;
    }

    public void setResearches(List<Research> researches) {
        this.researches = researches;
    }
}
