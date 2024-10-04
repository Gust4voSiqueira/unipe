package br.com.unipe.repository;

import br.com.unipe.entity.lostAndFoundItem.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query("SELECT i FROM Item i ORDER BY CASE WHEN i.user.id = :userId THEN 0 ELSE 1 END")
    List<Item> findAllItemsOrderedByUser(@Param("userId") Long userId, Pageable pageable);

}
