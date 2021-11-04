package com.budev.services;

import com.budev.dto.PublicationDto;
import com.budev.entities.Publication;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface PublicationService {
    void saveNewPublication(PublicationDto publication, Principal principal);
    List<Publication> findAllPublicationByUserId(int id);

    Optional<Publication> getPublicationById(int publicationId);

    void update(PublicationDto publicationDto, int publicationId);

    void remove(int publicationId);
}
