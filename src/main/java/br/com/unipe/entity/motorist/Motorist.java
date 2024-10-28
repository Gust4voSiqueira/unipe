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
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Motorist fromCreateMotoristRequest(CreateMotoristRequest createMotoristRequest, User user) {
        return Motorist.builder()
                .user(user)
                .city(createMotoristRequest.city())
                .neighborhood(createMotoristRequest.neighborhood())
                .car(createMotoristRequest.car())
                .plate(createMotoristRequest.plate())
                .availableDays(createMotoristRequest.days())
                .quantityVacancies(createMotoristRequest.quantityVacancies())
                .observation(createMotoristRequest.observation())
                .build();
    }

    public static Motorist fromUpdateMotorist(Motorist motorist, CreateMotoristRequest createMotoristRequest) {
        var motoristUpdated = Motorist.builder()
                .id(motorist.id)
                .user(motorist.user)
                .city(Objects.requireNonNullElse(createMotoristRequest.city(), motorist.city))
                .neighborhood(Objects.requireNonNullElse(createMotoristRequest.neighborhood(), motorist.neighborhood))
                .car(Objects.requireNonNullElse(createMotoristRequest.car(), motorist.car))
                .plate(Objects.requireNonNullElse(createMotoristRequest.plate(), motorist.plate))
                .quantityVacancies(Objects.requireNonNullElse(createMotoristRequest.quantityVacancies(), motorist.quantityVacancies))
                .observation(Objects.requireNonNullElse(createMotoristRequest.observation(), motorist.observation))
                .availableDays(motorist.availableDays)
                .build();

        if (Objects.nonNull(createMotoristRequest.days())) {
            motorist.getAvailableDays().clear();
            motorist.getAvailableDays().addAll(createMotoristRequest.days());
        }

        return motoristUpdated;
    }
}
