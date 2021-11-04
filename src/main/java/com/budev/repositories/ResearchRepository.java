package com.budev.repositories;

import com.budev.entities.Research;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResearchRepository extends JpaRepository<Research,Integer> {

    @Query(value = "SELECT * FROM researches WHERE user_id =:id",nativeQuery = true)
    List<Research> findAllByUserId(int id);
}
