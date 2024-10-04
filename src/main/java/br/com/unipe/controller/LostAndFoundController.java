package br.com.unipe.controller;

import br.com.unipe.entity.lostAndFoundItem.request.CreateItemRequest;
import br.com.unipe.entity.lostAndFoundItem.response.GetItemsResponse;
import br.com.unipe.entity.user.User;
import br.com.unipe.service.LostAndFoundService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("lostAndFound")
@RequiredArgsConstructor
public class LostAndFoundController {
    private final LostAndFoundService lostAndFoundService;

    @GetMapping("getItems")
    public ResponseEntity<List<GetItemsResponse>> getItems() {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        return ResponseEntity.ok().body(lostAndFoundService.getItems(user.getId()));
    }

    @PostMapping("createItem")
    public ResponseEntity createItem(@RequestBody @Valid CreateItemRequest createItemRequest) {
        User user = (User) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        lostAndFoundService.createItem(createItemRequest, user.getId());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("removeItem/{itemId}")
    public ResponseEntity removeItem(@PathVariable Long itemId) {
       try {
           User user = (User) SecurityContextHolder.getContext()
                   .getAuthentication()
                   .getPrincipal();

           lostAndFoundService.removeItem(itemId, user.getId());

           return ResponseEntity.ok().build();
       } catch (RuntimeException e) {
           return ResponseEntity.badRequest().body(e.getMessage());
       }
    }
}
