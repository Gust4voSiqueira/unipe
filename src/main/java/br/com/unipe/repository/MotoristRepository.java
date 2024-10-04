package br.com.unipe.repository;

import br.com.unipe.entity.motorist.Motorist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MotoristRepository extends JpaRepository<Motorist, Long> {
    Optional<List<Motorist>> findAllByCity(String city);
}
