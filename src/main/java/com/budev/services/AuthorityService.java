package com.budev.services;


import com.budev.dto.AuthorityDto;
import com.budev.entities.Authority;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface AuthorityService {

    void save(Authority adminRole);

    void createNewRole(AuthorityDto authorityDto);

    Optional<Authority> findByAuthority(String authorityName);

    List<Authority> findAllAuthority();
}
