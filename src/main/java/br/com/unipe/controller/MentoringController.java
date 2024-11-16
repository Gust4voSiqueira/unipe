package br.com.unipe.controller;

import br.com.unipe.entity.mentoring.request.CreateMentoringRequest;
import br.com.unipe.entity.mentoring.response.ListMentoringsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.MentoringService;
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

import java.util.List;

@RestController
@RequestMapping("mentoring")
@RequiredArgsConstructor
public class MentoringController {

    private final MentoringService mentoringService;

    @PostMapping
    public ResponseEntity insertMentoring(@RequestBody CreateMentoringRequest createMentoringRequest) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        mentoringService.createMentoring(createMentoringRequest, user.getId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ListMentoringsResponse>> listMentorings() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok().body(mentoringService.listMentorings(user.getId()));
    }

    @DeleteMapping("/delete/{mentoringId}")
    public ResponseEntity deleteMentoring(@PathVariable Long mentoringId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        mentoringService.deleteMentoring(mentoringId, user.getId());

        return ResponseEntity.ok().build();
    }
}
