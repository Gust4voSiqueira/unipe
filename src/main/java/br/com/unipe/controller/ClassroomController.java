package br.com.unipe.controller;

import br.com.unipe.entity.classroomMap.request.RegisterClassroomRequest;
import br.com.unipe.entity.classroomMap.response.ListClassRoomResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.ClassroomMapService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("classroom")
@RequiredArgsConstructor
public class ClassroomController {

    private final ClassroomMapService classroomMapService;

    @PostMapping
    public ResponseEntity requestClassroom(@RequestBody RegisterClassroomRequest request) {
        try {
            User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            classroomMapService.registerRoom(request, user.getId());

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<ListClassRoomResponse> listClassroom() {
        try {
            User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            return ResponseEntity.ok().body(classroomMapService.listRoom(user.getId()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{classroomId}")
    public ResponseEntity deleteClassroom(@PathVariable Long classroomId) {
        try {
            User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            classroomMapService.deleteRoom(classroomId, user.getId());

            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
