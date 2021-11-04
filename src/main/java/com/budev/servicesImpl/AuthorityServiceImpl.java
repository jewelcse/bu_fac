package com.budev.servicesImpl;

import com.budev.dto.AuthorityDto;
import com.budev.entities.Authority;
import com.budev.repositories.AuthorityRepository;
import com.budev.services.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorityServiceImpl implements AuthorityService {


    private AuthorityRepository authorityRepository;

    @Autowired
    public AuthorityServiceImpl(AuthorityRepository authorityRepository){
        this.authorityRepository = authorityRepository;
    }


    @Override
    public void save(Authority adminRole) {
        authorityRepository.save(adminRole);
    }

    @Override
    public void createNewRole(AuthorityDto authorityDto) {
        Authority newAuthority = new Authority();
        newAuthority.setAuthority(authorityDto.getAuthorityName().toUpperCase());
        authorityRepository.save(newAuthority);
    }

    @Override
    public Optional<Authority> findByAuthority(String authorityName) {
        return authorityRepository.findByAuthority(authorityName);
    }

    @Override
    public List<Authority> findAllAuthority() {
        return authorityRepository.findAll();
    }
}
