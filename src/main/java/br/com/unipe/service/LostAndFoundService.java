package br.com.unipe.service;

import br.com.unipe.entity.lostAndFoundItem.request.CreateItemRequest;
import br.com.unipe.entity.lostAndFoundItem.response.GetItemsResponse;
import br.com.unipe.exceptions.ItemNotFoundException;
import br.com.unipe.exceptions.UnauthorizedItemDeletionException;
import br.com.unipe.exceptions.UserNotFoundException;
import br.com.unipe.repository.ItemRepository;
import br.com.unipe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static br.com.unipe.entity.lostAndFoundItem.Item.fromCreateItemRequest;

@Service
@RequiredArgsConstructor
public class LostAndFoundService {
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    public List<GetItemsResponse> getItems(Long userId) {
        Pageable pageable = PageRequest.of(0, 30);
        var response = itemRepository.findAllItemsOrderedByUser(userId, pageable);

        return response.stream().map(item -> {
            var isAddedByCurrentUser = item.getUser().getId().equals(userId);
            return GetItemsResponse.fromItem(item, isAddedByCurrentUser);
        }).toList();
    }

    public void createItem(CreateItemRequest createItemRequest, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));;

        var item = fromCreateItemRequest(createItemRequest, user);

        itemRepository.save(item);
    }

    public void removeItem(Long itemId, Long userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuário não encontrado."));

        var item = itemRepository.findById(itemId)
                .orElseThrow(() -> new ItemNotFoundException("Item não encontrado."));

        if (!item.getUser().getId().equals(user.getId())) {
            throw new UnauthorizedItemDeletionException("Este item não pertence a este usuário, portanto você não pode apagá-lo.");
        }

        itemRepository.deleteById(item.getId());
    }

}
