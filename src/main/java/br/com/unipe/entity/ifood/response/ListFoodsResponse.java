package br.com.unipe.entity.ifood.response;

import br.com.unipe.entity.ifood.Food;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ListFoodsResponse {
    private String food;
    private Long id;
    private List<String> availableDays;
    private Boolean isAddedByCurrentUser;
    private Boolean isOnline;
    private String phone;

    public static ListFoodsResponse fromFood(Food food, boolean isAddedByCurrentUser) {
        return ListFoodsResponse.builder()
                .food(food.getFood())
                .id(food.getId())
                .availableDays(food.getAvailableDays())
                .isAddedByCurrentUser(isAddedByCurrentUser)
                .phone(food.getUser().getPhone())
                .isOnline(food.getIsOnline())
                .build();
    }
}
