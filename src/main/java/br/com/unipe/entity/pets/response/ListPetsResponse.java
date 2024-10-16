package br.com.unipe.entity.pets.response;

import br.com.unipe.entity.pets.Pet;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListPetsResponse {
    private String pet;
    private String description;
    private String phone;
    private Boolean isAddedByCurrentUser;

    public static ListPetsResponse fromPet(Pet pet, boolean isAddedByCurrentUser) {
        return ListPetsResponse.builder()
                .pet(pet.getPet())
                .description(pet.getDescription())
                .phone(pet.getUser().getPhone())
                .isAddedByCurrentUser(isAddedByCurrentUser)
                .build();
    }
}
