package br.com.unipe.entity.motorist.response;

import br.com.unipe.entity.motorist.Motorist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static br.com.unipe.entity.motorist.response.GetMotoristResponse.fromMotorist;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class IsExistsMotoristRegistered {
    private Boolean isExistsCarRegistered;
    private GetMotoristResponse car;

    public static IsExistsMotoristRegistered fromNotExistsMotoristRegistered() {
        return IsExistsMotoristRegistered.builder()
                .isExistsCarRegistered(false)
                .build();
    }

    public static IsExistsMotoristRegistered fromExistsMotoristRegistered(Motorist motorist) {
        return IsExistsMotoristRegistered.builder()
                .isExistsCarRegistered(true)
                .car(fromMotorist(motorist))
                .build();
    }
}
