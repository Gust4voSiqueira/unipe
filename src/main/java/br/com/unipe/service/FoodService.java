package br.com.unipe.service;

import br.com.unipe.entity.ifood.request.InsertFoodRequest;
import br.com.unipe.entity.ifood.response.ListFoodsResponse;
import br.com.unipe.exceptions.FoodNotFoundException;
import br.com.unipe.exceptions.ItemNotFoundException;
import br.com.unipe.exceptions.UnauthorizedItemDeletionException;
import br.com.unipe.exceptions.UnauthorizedProductUpdateException;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.FoodRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.unipe.entity.ifood.Food.fromInsertFoodRequest;
import static br.com.unipe.entity.ifood.Food.updateStatusFood;
import static br.com.unipe.entity.ifood.response.ListFoodsResponse.fromFood;

@Service
@RequiredArgsConstructor
public class FoodService {
    private final FoodRepository foodRepository;
    private final UserRepository userRepository;

    public void insertFood(InsertFoodRequest insertFoodRequest, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        foodRepository.save(fromInsertFoodRequest(insertFoodRequest, user));
    }

    public void deleteFood(Long foodId, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new ItemNotFoundException("Produto não encontrado."));

        if(!food.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedItemDeletionException("Você não pode deletar um produto que não foi cadastrado por você.");
        }

        foodRepository.delete(food);
    }

    public List<ListFoodsResponse> listFoods(Long userId) {
        var foods = foodRepository.findAllFoodsOrderedByUser(userId, PageRequest.of(0, 30));

        return foods
                .stream()
                .map(food -> {
                    var isAddedByCurrentUser = food.getUser().getId().equals(userId);
                    return fromFood(food, isAddedByCurrentUser);
                })
                .toList();
    }

    public void updateOnlineStatusFood(Long foodId, Long userId, Boolean newStatus) {
        var food = foodRepository.findById(foodId)
                .orElseThrow(() -> new FoodNotFoundException("Produto não encontrada."));

        if(!food.getUser().getId().equals(userId)) {
            throw new UnauthorizedProductUpdateException("Você não pode alterar o status de um produto que não é seu.");
        }

        var newFood = updateStatusFood(food, newStatus);

        foodRepository.save(newFood);
    }
}
