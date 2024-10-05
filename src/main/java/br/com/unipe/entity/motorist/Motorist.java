package br.com.unipe.entity.motorist;

import br.com.unipe.entity.motorist.request.CreateMotoristRequest;
import br.com.unipe.entity.user.User;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Motorist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phone;
    private String city;
    private String neighborhood;
    private String car;
    private String plate;
    private Integer quantityVacancies;
    private String observation;

    @ElementCollection
    @CollectionTable(name = "user_available_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "days")
    private List<String> availableDays;

    public static Motorist fromCreateMotoristRequest(CreateMotoristRequest createMotoristRequest, User user) {
        return Motorist.builder()
                .phone(user.getPhone())
                .name(user.getName())
                .city(createMotoristRequest.city())
                .neighborhood(createMotoristRequest.neighborhood())
                .car(createMotoristRequest.car())
                .plate(createMotoristRequest.plate())
                .availableDays(createMotoristRequest.days())
                .quantityVacancies(createMotoristRequest.quantityVacancies())
                .observation(createMotoristRequest.observation())
                .build();
    }
}
