package br.com.unipe.entity.ifood;

import br.com.unipe.entity.ifood.request.InsertFoodRequest;
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

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String food;
    private Boolean isOnline;
    @ElementCollection
    @CollectionTable(name = "user_ifood_available_days", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "days")
    private List<String> availableDays;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Food fromInsertFoodRequest(InsertFoodRequest insertFoodRequest, User user) {
        return Food.builder()
                .food(insertFoodRequest.food())
                .availableDays(insertFoodRequest.days())
                .user(user)
                .isOnline(false)
                .build();
    }

    public static Food updateStatusFood(Food food, Boolean newStatus) {
        return Food.builder()
                .id(food.getId())
                .food(food.getFood())
                .availableDays(food.getAvailableDays())
                .user(food.user)
                .isOnline(newStatus)
                .build();
    }
}
