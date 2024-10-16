package br.com.unipe.controller;

import br.com.unipe.entity.pets.request.InsertPetRequest;
import br.com.unipe.entity.pets.response.ListPetsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.PetService;
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
@RequestMapping("pet")
@RequiredArgsConstructor
public class PetController {
    PetService petService;

    @PostMapping("insert")
    public ResponseEntity insertPet(@RequestBody InsertPetRequest insertPetRequest) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        petService.insertPet(insertPetRequest, user.getId());

        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<ListPetsResponse>> listPets() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        petService.listPets(user.getId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{petId}")
    public ResponseEntity deletePet(@PathVariable Long petId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        petService.deletePet(petId, user.getId());

        return ResponseEntity.ok().build();
    }
}
