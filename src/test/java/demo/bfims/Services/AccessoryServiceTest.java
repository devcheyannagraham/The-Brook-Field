package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.AccessoryType;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AccessoryServiceTest {

    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    AccessoryService accessoryService;

    @Autowired
    ItemRepo itemRepo;

    static AccessoryItem availableAccessoryItem;
    static AccessoryItem unavailableAccessoryItem;
    static Accessory accessory;

    @Test
    @Order(1)
    void newAccessory() {
        accessory = accessoryRepo.save(new Accessory("Accessory Test", AccessoryType.BOOKMARK, 2.99));
        availableAccessoryItem = itemRepo.save(new AccessoryItem(accessory, AccessoryItemStatus.AVAILABLE));
        unavailableAccessoryItem = itemRepo.save(new AccessoryItem(accessory, AccessoryItemStatus.PURCHASED));
        assertNotNull(accessory);
    }

    @Test
    @Order(2)
    void getAccessories() {
        List<AccessoryDto> accessoryDtos = accessoryRepo.findAll().stream().map(AccessoryDto::new).toList();
        assertEquals(accessoryService.getAccessories().size(), accessoryDtos.size());
    }

    @Test
    @Order(3)
    void getAccessoryById() {
        assertEquals(this.accessoryService.getAccessoryById(accessory.getAccessoryId()).getAccessoryId(), new AccessoryDto(accessory).getAccessoryId());
        assertNull(this.accessoryService.getAccessoryById(null));
    }

    @Test
    @Order(4)
    void getAvailableAccessoryItemsByAccessoryId() {
        assertEquals(1, this.accessoryService.getAvailableAccessoryItemsByAccessoryId(accessory.getAccessoryId()).size());
        assertNull(this.accessoryService.getAvailableAccessoryItemsByAccessoryId(null));
    }

    @Test
    @Order(5)
    void getAccessoryItemsByAccessoryId() {
        assertEquals(2, this.accessoryService.getAccessoryItemsByAccessoryId(accessory.getAccessoryId()).size());
        assertNull(this.accessoryService.getAccessoryItemsByAccessoryId(null));
    }


    @Test
    @Order(6)
    void deleteAccessoryItemById() {
        this.accessoryService.deleteAccessoryItemById(unavailableAccessoryItem.getItemId());
        assertNull(this.accessoryService.getAccessoryById(unavailableAccessoryItem.getItemId()));
    }

    @Test
    @Order(7)
    void deleteAccessory() {
        assertThrows(DataIntegrityViolationException.class, () -> accessoryService.deleteAccessory(accessory.getAccessoryId()));
        itemRepo.deleteById(availableAccessoryItem.getItemId());
        this.accessoryService.deleteAccessory(accessory.getAccessoryId());
        assertNull(this.accessoryService.getAccessoryById(accessory.getAccessoryId()));
    }
}