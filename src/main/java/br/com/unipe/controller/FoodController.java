package br.com.unipe.controller;

import br.com.unipe.entity.ifood.request.InsertFoodRequest;
import br.com.unipe.entity.ifood.response.ListFoodsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("food")
@RequiredArgsConstructor
public class FoodController {
    private final FoodService foodService;

    @GetMapping
    public ResponseEntity<List<ListFoodsResponse>> listFoods() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok().body(foodService.listFoods(user.getId()));
    }

    @PostMapping("insertFood")
    public ResponseEntity insertFood(@RequestBody InsertFoodRequest insertFoodRequest) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        foodService.insertFood(insertFoodRequest, user.getId());

        return ResponseEntity.ok().build();
    }

    @PutMapping("updateStatus/{foodId}/{newStatus}")
    public ResponseEntity updateStatus(@PathVariable Long foodId, @PathVariable Boolean newStatus) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        foodService.updateOnlineStatusFood(foodId, user.getId(), newStatus);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("delete/{foodId}")
    public ResponseEntity deleteFood(@PathVariable Long foodId) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        foodService.deleteFood(foodId, user.getId());

        return ResponseEntity.ok().build();
    }
}
