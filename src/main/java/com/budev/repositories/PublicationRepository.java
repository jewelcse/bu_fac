package com.budev.repositories;

import com.budev.entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Integer> {

    @Query(value = "SELECT * FROM publications WHERE user_id =:id",nativeQuery = true)
    List<Publication> findAllByUserId(int id);
}
