package br.com.unipe.service;

import br.com.unipe.entity.classroomMap.request.RegisterClassroomRequest;
import br.com.unipe.entity.classroomMap.response.ListClassRoomResponse;
import br.com.unipe.entity.user.Role;
import br.com.unipe.exceptions.ItemNotFoundException;
import br.com.unipe.exceptions.UnauthorizedClassroomDelete;
import br.com.unipe.exceptions.UnauthorizedClassroomInsert;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.ClassroomRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.unipe.entity.classroomMap.ClassroomMap.fromRegisterClassroomRequest;
import static br.com.unipe.entity.classroomMap.response.ListClassRoomResponse.fromClassroomMap;

@Service
@RequiredArgsConstructor
public class ClassroomMapService {

    private final UserRepository userRepository;
    private final ClassroomRepository classroomRepository;

    public void registerRoom(RegisterClassroomRequest request, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        if(!user.getRole().equals(Role.ADMIN)) {
            throw new UnauthorizedClassroomInsert("Somente Admins podem criar salas.");
        }

        var classroom = fromRegisterClassroomRequest(request, user);

        classroomRepository.save(classroom);
    }

    public void deleteRoom(Long classRoomId, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var classroom = classroomRepository.findById(classRoomId)
                .orElseThrow(() -> new ItemNotFoundException("Sala de aula não encontrada."));

        if(!classroom.getTeacher().equals(user)) {
            throw new UnauthorizedClassroomDelete("Somente o professor responsável pode deletadar esta sala de aula.");
        }

        classroomRepository.delete(classroom);
    }

    public ListClassRoomResponse listRoom(Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));
        var isTeacher = user.getRole().equals(Role.ADMIN);

        var classroomList = classroomRepository.findAll();

        return fromClassroomMap(isTeacher, classroomList);
    }
}
