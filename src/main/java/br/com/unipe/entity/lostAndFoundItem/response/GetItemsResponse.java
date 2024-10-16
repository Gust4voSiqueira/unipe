package br.com.unipe.entity.lostAndFoundItem.response;

import br.com.unipe.entity.lostAndFoundItem.Item;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GetItemsResponse {
    private Long id;
    private String item;
    private String local;
    private String phone;
    private Boolean isAddedByCurrentUser;

    public static GetItemsResponse fromItem(Item item, Boolean isAddedByCurrentUser) {
        return GetItemsResponse.builder()
                .id(item.getId())
                .item(item.getName())
                .local(item.getLocal())
                .phone(item.getUser().getPhone())
                .isAddedByCurrentUser(isAddedByCurrentUser)
                .build();
    }
}
