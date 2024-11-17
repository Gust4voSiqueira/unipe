package br.com.unipe.repository;

import br.com.unipe.entity.classroomMap.ClassroomMap;
import br.com.unipe.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<ClassroomMap, Long> {
    List<ClassroomMap> findByTeacher(User user);
}
