package br.com.unipe.entity.pets.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InsertPetRequest {
    private String pet;
    private String description;
}
