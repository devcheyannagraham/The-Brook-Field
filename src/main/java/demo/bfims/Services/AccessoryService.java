package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Repo.AccessoryItemRepo;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessoryService {

    private final AccessoryRepo accessoryRepo;
    private final ItemRepo itemRepo;
    private final AccessoryItemRepo accessoryItemRepo;

    public AccessoryService(AccessoryRepo accessoryRepo, ItemRepo itemRepo, AccessoryItemRepo accessoryItemRepo) {
        this.accessoryRepo = accessoryRepo;
        this.itemRepo = itemRepo;
        this.accessoryItemRepo = accessoryItemRepo;
    }

    //READS
    public List<AccessoryDto> getAccessories() {
        List<Accessory> accessories = accessoryRepo.findAll();
        if (!accessories.isEmpty()) {
            return accessories.stream().map(AccessoryDto::new).collect(Collectors.toList());
        }
        return null;
    }


    public AccessoryDto getAccessoryById(Long id) {
        if(id == null) return null;
        Accessory accessory = accessoryRepo.findById(id).orElse(null);
        if (accessory != null) {
            return new AccessoryDto(accessory);
        }
        return null;
    }

    public List<AccessoryItemDto> getAvailableAccessoryItemsByAccessoryId(Long accessoryId) {
        if(accessoryId == null) return null;
        List<AccessoryItem> items = accessoryItemRepo.findAccessoryItemByAccessory_AccessoryIdAndAccessoryItemStatus(accessoryId, AccessoryItemStatus.AVAILABLE);
        if (items == null) return null;
        return items.stream().map(AccessoryItemDto::new).toList();
    }

    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(Long accessoryId) {
        if(accessoryId == null) return null;
        List<AccessoryItem> items = accessoryItemRepo.findAccessoryItemsByAccessory_AccessoryId(accessoryId);
        if (items == null) return null;
        return items.stream().map(AccessoryItemDto::new).toList();
    }


    public AccessoryDto newAccessory(AccessoryDto accessoryDto) {
        if(accessoryDto == null) return null;
        Accessory accessory = new Accessory(accessoryDto);
        Accessory savedAccessory = accessoryRepo.save(accessory);
        //create items
        for (int i = 0; i < accessoryDto.getQuantity(); i++) {
            AccessoryItem accessoryItem = new AccessoryItem();
            accessoryItem.setAccessoryItemStatus(AccessoryItemStatus.AVAILABLE);
            accessoryItem.setAccessory(savedAccessory);
            itemRepo.save(accessoryItem);
        }
        return new AccessoryDto(savedAccessory);
    }

    //DELETES

    @Transactional
    public Integer deleteAccessoryItemById(Long id) {
        if(id == null) return null;
        return itemRepo.deleteItemByItemId(id);
    }

    @Transactional
    public Integer deleteAccessoryById(Long id) {
        if(id == null) return null;
        return accessoryRepo.deleteByAccessoryId(id);
    }


}
