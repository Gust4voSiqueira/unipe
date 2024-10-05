package br.com.unipe.entity.motorist.response;

import br.com.unipe.entity.motorist.Motorist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetMotoristResponse {
    private String name;
    private String phone;
    private String city;
    private String neighborhood;
    private String car;
    private String plate;
    private Integer quantityVacancies;
    private String observation;
    private List<String> availableDays;


    public static GetMotoristResponse fromMotorist(Motorist motorist) {
        return GetMotoristResponse.builder()
                .name(motorist.getName())
                .phone(motorist.getPhone())
                .city(motorist.getCity())
                .neighborhood(motorist.getNeighborhood())
                .car(motorist.getCar())
                .plate(motorist.getPlate())
                .availableDays(motorist.getAvailableDays())
                .quantityVacancies(motorist.getQuantityVacancies())
                .observation(motorist.getObservation())
                .build();
    }

}
