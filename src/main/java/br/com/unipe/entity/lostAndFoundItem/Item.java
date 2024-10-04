package br.com.unipe.entity.lostAndFoundItem;

import br.com.unipe.entity.lostAndFoundItem.request.CreateItemRequest;
import br.com.unipe.entity.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "LostAndFoundItem")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String local;
    private String observation;
    private String numberContact;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public static Item fromCreateItemRequest(CreateItemRequest createItemRequest, User user) {
        return Item.builder()
                .name(createItemRequest.item())
                .local(createItemRequest.local())
                .observation(createItemRequest.observation())
                .numberContact(createItemRequest.numberContact())
                .user(user)
                .build();
    }
}
