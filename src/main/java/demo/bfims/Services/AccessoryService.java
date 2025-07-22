package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.AccessoryType;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessoryService {

    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EntityManager entityManager;

    public AccessoryItemDto newAccessory() {
        System.out.println("newAccessory in service");
        Accessory accessory = new Accessory();
        accessory.setAccessoryType(AccessoryType.BOOKMARK);
        accessory.setAccessoryName("flower/blue");
        accessory.setPrice(1.29);
        Accessory savedAccessory = accessoryRepo.save(accessory);

        AccessoryItem accessoryItem = new AccessoryItem();
        accessoryItem.setAccessory(savedAccessory);

        AccessoryItem savedAccessoryItem = itemRepo.save(accessoryItem);
        System.out.println("accessoryItem saved in service" + savedAccessoryItem);

        return modelMapper.map(savedAccessoryItem, AccessoryItemDto.class);
    }
}
