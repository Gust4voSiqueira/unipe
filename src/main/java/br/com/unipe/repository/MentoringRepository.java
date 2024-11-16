package br.com.unipe.repository;

import br.com.unipe.entity.mentoring.Mentoring;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MentoringRepository extends JpaRepository<Mentoring, Long> {
    List<Mentoring> findByCourse(String course);
}
