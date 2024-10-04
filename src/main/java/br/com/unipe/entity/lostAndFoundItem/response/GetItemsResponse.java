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
    private String item;
    private String local;
    private String phone;
    private String observation;

    public static GetItemsResponse fromItem(Item item) {
        return GetItemsResponse.builder()
                .item(item.getName())
                .local(item.getLocal())
                .observation(item.getObservation())
                .phone(item.getPhone())
                .build();
    }
}
