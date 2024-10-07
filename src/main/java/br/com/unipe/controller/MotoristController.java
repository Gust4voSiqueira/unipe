package br.com.unipe.controller;

import br.com.unipe.entity.motorist.request.CreateMotoristRequest;
import br.com.unipe.entity.motorist.response.GetMotoristResponse;
import br.com.unipe.entity.motorist.response.IsExistsMotoristRegistered;
import br.com.unipe.entity.motorist.response.ListMotoristsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.MotoristService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("motorist")
@RequiredArgsConstructor
public class MotoristController {
    private final MotoristService motoristService;

    @GetMapping("listMotorists/{city}")
    public ResponseEntity<List<ListMotoristsResponse>> getAllMotorists(@PathVariable String city) {
        try {
            return ResponseEntity.ok().body(motoristService.getMotorists(city));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @PostMapping("createMotorist")
    public ResponseEntity createMotorist(@RequestBody CreateMotoristRequest createMotoristRequest) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        motoristService.createMotorist(createMotoristRequest, user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("getMotorist/{idMotorist}")
    public ResponseEntity<GetMotoristResponse> getDetailsMotorist(@PathVariable Long idMotorist) {
        try {
            return ResponseEntity.ok().body(motoristService.getDetailsMotorist(idMotorist));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("isExistsCarRegistered")
    public ResponseEntity<IsExistsMotoristRegistered> isExistsCarRegistered() {
        try {
            User user = (User) SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getPrincipal();

            return ResponseEntity.ok().body(motoristService.isExistsCarRegistered(user.getId()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
