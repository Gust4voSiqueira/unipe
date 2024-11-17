package br.com.unipe.repository;

import br.com.unipe.entity.classroomMap.ClassroomMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomMap, Long> {
}
