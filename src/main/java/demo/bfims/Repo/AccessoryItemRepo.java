package demo.bfims.Repo;

import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.AccessoryItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccessoryItemRepo extends JpaRepository<AccessoryItem, Long> {
    List<AccessoryItem> findAccessoryItemsByAccessory_AccessoryId(Long accessoryId);

    //Find any available accessoryItem that matches the accessoryId
    AccessoryItem findTopAccessoryItemByAccessory_AccessoryIdAndAccessoryItemStatus(Long accessoryId, AccessoryItemStatus accessoryItemStatus);

    Integer countAccessoryItemsByAccessory_AccessoryId(Long id);

}
