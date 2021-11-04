package com.budev.repositories;

import com.budev.entities.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,Integer> {

    @Query(value = "SELECT * FROM authorities WHERE authority =:authorityName",nativeQuery = true)
    Optional<Authority> findByAuthority(String authorityName);
}
