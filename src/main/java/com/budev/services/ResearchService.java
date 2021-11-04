package com.budev.services;

import com.budev.dto.ResearchDto;
import com.budev.entities.Research;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

public interface ResearchService {
    void saveNewResearch(ResearchDto research, Principal principal);
    List<Research> findAllResearchByUserId(int id);

    Optional<Research> getResearchById(int researchId);

    void update(ResearchDto researchDto, int researchId);

    void remove(int researchId);
}
