package demo.bfims.Services;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.ResponseType;
import demo.bfims.Repo.AccessoryItemRepo;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccessoryService {

    @Autowired
    AccessoryRepo accessoryRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    AccessoryItemRepo accessoryItemRepo;

    //READS
    public List<AccessoryDto> getAccessories() {
        List<Accessory> accessories = accessoryRepo.findAll();
        if (!accessories.isEmpty()) {
            return accessories.stream().map(AccessoryDto::new).collect(Collectors.toList());
        }
        return null;
    }


    public AccessoryDto getAccessory(Long id) {
        Accessory accessory = accessoryRepo.findById(id).orElse(null);
        if (accessory != null) {
            return new AccessoryDto(accessory);
        }
        return null;
    }

    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(Long accessoryId) {
        List<AccessoryItem> items = accessoryItemRepo.findAccessoryItemsByAccessory_AccessoryId(accessoryId);
        if (items == null) return null;
        return items.stream().map(AccessoryItemDto::new).toList();
    }

    public List<AccessoryItemDto> getAvailableAccessoryItemsByAccessoryId(Long accessoryId) {
        List<AccessoryItem> items = accessoryItemRepo.findAccessoryItemsByAccessory_AccessoryId(accessoryId);
        System.out.println("Available Accessory Items: " + items);
        if (items == null) return null;

        return items.stream().filter(item -> item.getAccessoryItemStatus().equals(AccessoryItemStatus.AVAILABLE)).map(AccessoryItemDto::new).toList();
    }


    public AccessoryDto newAccessory(AccessoryDto accessoryDto) {
        Accessory accessory = new  Accessory(accessoryDto);
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
    public Response deleteAccessory(Long id) {
        Response response = new Response();
        Integer rows = accessoryRepo.deleteByAccessoryId(id);
        response.getMessages().put(ResponseType.SUCCESS.toString(), "Accessory has been removed successfully");
        response.getMessages().put(ResponseType.MESSAGE.toString(), rows.toString() + " affected.");
        return response;
    }

    @Transactional
    public Boolean deleteAccessoryItemById(Long id) {
        try {
            return itemRepo.deleteItemByItemId(id) >= 0;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public AccessoryItemDto updateAccessoryItem(AccessoryItemDto accessoryItemDto) {
        AccessoryItem accessoryItem = new AccessoryItem(accessoryItemDto);
        Accessory managedAccessory = entityManager.merge(accessoryItem.getAccessory());
        accessoryItem.setAccessory(managedAccessory);
        return new AccessoryItemDto(itemRepo.save(accessoryItem));
    }

}
