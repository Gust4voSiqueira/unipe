package br.com.unipe.entity.pets;

import br.com.unipe.entity.pets.request.InsertPetRequest;
import br.com.unipe.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pet;
    private String description;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Pet fromInsertPetRequest(InsertPetRequest insertPetRequest, User user) {
        return Pet.builder()
                .pet(insertPetRequest.getPet())
                .description(insertPetRequest.getDescription())
                .user(user)
                .build();
    }
}
