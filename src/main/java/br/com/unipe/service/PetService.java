package br.com.unipe.service;

import br.com.unipe.entity.lostAndFoundItem.response.GetItemsResponse;
import br.com.unipe.entity.pets.Pet;
import br.com.unipe.entity.pets.request.InsertPetRequest;
import br.com.unipe.entity.pets.response.ListPetsResponse;
import br.com.unipe.exceptions.PetNotFoundException;
import br.com.unipe.exceptions.UnauthorizedItemDeletionException;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.PetRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.unipe.entity.pets.Pet.fromInsertPetRequest;

@Service
@RequiredArgsConstructor
public class PetService {
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    public List<ListPetsResponse> listPets(Long userId) {
        var pets = petRepository.findAllPetsOrderedByUser(userId, PageRequest.of(0, 30));

        return pets
                .stream()
                .map(pet -> {
                    var isAddedByCurrentUser = pet.getUser().getId().equals(userId);
                    return ListPetsResponse.fromPet(pet, isAddedByCurrentUser);
                })
                .toList();
    }

    public void insertPet(InsertPetRequest insertPetRequest, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        petRepository.save(fromInsertPetRequest(insertPetRequest, user));
    }

    public void deletePet(Long petId, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var pet = petRepository.findById(petId)
                .orElseThrow(() -> new PetNotFoundException("Pet não encontrado."));

        if(!pet.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedItemDeletionException("Você não pode deletar um pet que não foi cadastrado por você.");
        }

        petRepository.delete(pet);
    }
}
