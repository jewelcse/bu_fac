package com.budev.servicesImpl;

import com.budev.dto.PublicationDto;
import com.budev.entities.Publication;
import com.budev.entities.User;
import com.budev.repositories.PublicationRepository;
import com.budev.repositories.UserRepository;
import com.budev.services.PublicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private PublicationRepository publicationRepository;
    private UserRepository userRepository;

    @Autowired
    public PublicationServiceImpl(PublicationRepository publicationRepository,UserRepository userRepository){
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveNewPublication(PublicationDto publication, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        Publication newPublication = new Publication();
        newPublication.setTitle(publication.getTitle());
        newPublication.setBody(publication.getBody());
        newPublication.setUser(user);
        publicationRepository.save(newPublication);
    }

    @Override
    public List<Publication> findAllPublicationByUserId(int id) {
        return publicationRepository.findAllByUserId(id);
    }

    @Override
    public Optional<Publication> getPublicationById(int publicationId) {
        return publicationRepository.findById(publicationId);
    }

    @Override
    public void update(PublicationDto publicationDto, int publicationId) {
        Optional<Publication> publication = publicationRepository.findById(publicationId);
        publication.get().setPublication_id(publicationId);
        publication.get().setTitle(publicationDto.getTitle());
        publication.get().setBody(publicationDto.getBody());
        publicationRepository.save(publication.get());
    }

    @Override
    public void remove(int publicationId) {
        publicationRepository.deleteById(publicationId);
    }
}
