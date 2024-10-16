package br.com.unipe.repository;

import br.com.unipe.entity.pets.Pet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PetRepository extends JpaRepository<Pet, Long> {
    @Query("SELECT i FROM Pet i ORDER BY CASE WHEN i.user.id = :userId THEN 0 ELSE 1 END")
    List<Pet> findAllPetsOrderedByUser(@Param("userId") Long userId, Pageable pageable);

}
