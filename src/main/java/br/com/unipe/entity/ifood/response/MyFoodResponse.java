package br.com.unipe.entity.ifood.response;

import br.com.unipe.entity.ifood.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MyFoodResponse {
    private Boolean isExistsFood;
    private Long id;
    private Boolean isOnline;

    public static MyFoodResponse fromFoodIsNotExists() {
        return MyFoodResponse.builder()
                .isExistsFood(false)
                .build();
    }

    public static MyFoodResponse fromFoodIsExistsFood(Food food) {
        return MyFoodResponse.builder()
                .isExistsFood(true)
                .id(food.getId())
                .isOnline(food.getIsOnline())
                .build();
    }
}
