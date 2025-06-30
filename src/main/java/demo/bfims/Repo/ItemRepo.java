package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Enums.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepo extends JpaRepository<Item,Long> {
    Optional<List<Item>> findItemsByItemType(ItemType itemType);
}
