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
public class ListMotoristsResponse {
    private String name;
    private String neighborhood;
    private List<String> availableDays;

    public static ListMotoristsResponse fromMotorist(Motorist motorist) {
        return ListMotoristsResponse.builder()
                .name(motorist.getName())
                .neighborhood(motorist.getNeighborhood())
                .availableDays(motorist.getAvailableDays())
                .build();
    }
}
