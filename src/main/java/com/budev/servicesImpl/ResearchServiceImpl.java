package com.budev.servicesImpl;

import com.budev.dto.ResearchDto;
import com.budev.entities.Research;
import com.budev.entities.User;
import com.budev.repositories.ResearchRepository;
import com.budev.repositories.UserRepository;
import com.budev.services.ResearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
public class ResearchServiceImpl implements ResearchService {

    private ResearchRepository researchRepository;
    private UserRepository userRepository;

    @Autowired
    public ResearchServiceImpl(ResearchRepository researchRepository,UserRepository userRepository){
        this.researchRepository  = researchRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveNewResearch(ResearchDto research, Principal principal) {

        User user = userRepository.findByUsername(principal.getName());

        Research newResearch = new Research();
        newResearch.setTitle(research.getTitle());
        newResearch.setBody(research.getBody());
        newResearch.setUser(user);
        researchRepository.save(newResearch);
    }

    @Override
    public List<Research> findAllResearchByUserId(int id) {
        return researchRepository.findAllByUserId(id);
    }

    @Override
    public Optional<Research> getResearchById(int researchId) {
        return researchRepository.findById(researchId);
    }

    @Override
    public void update(ResearchDto researchDto, int researchId) {

        Optional<Research> research = researchRepository.findById(researchId);
        research.get().setResearch_id(researchId);
        research.get().setTitle(researchDto.getTitle());
        research.get().setBody(researchDto.getBody());
        researchRepository.save(research.get());

    }

    @Override
    public void remove(int researchId) {
        researchRepository.deleteById(researchId);
    }
}
