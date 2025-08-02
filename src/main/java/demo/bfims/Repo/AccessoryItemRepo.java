package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoryItemRepo extends JpaRepository<AccessoryItem, Long> {
    List<AccessoryItem> findAccessoryItemsByAccessory_AccessoryId(Long accessoryId);
}
