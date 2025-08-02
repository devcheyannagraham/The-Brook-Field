package demo.bfims.Services;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ResponseType;
import demo.bfims.Repo.AccessoryItemRepo;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
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
    ModelMapper modelMapper;

    @Autowired
    EntityManager entityManager;

    @Autowired
    AccessoryItemRepo accessoryItemRepo;

    //READS
    public List<AccessoryDto> getAccessories() {
        List<Accessory> accessories = accessoryRepo.findAll();
        if (!accessories.isEmpty()) {
            return accessories.stream().map(acc -> modelMapper.map(acc, AccessoryDto.class)).collect(Collectors.toList());
        }
        return null;
    }

//    public List<AccessoryItemDto> getAccessoryItems() {
//        List<Item> accessoryItems = itemRepo.findItemsByItemType(ItemType.ACCESSORY_ITEM).orElse(null);
//        System.out.println("accessoryItems: " + accessoryItems);
//        if (accessoryItems != null && !accessoryItems.isEmpty()) {
//            return accessoryItems.stream().map(item -> {
//                AccessoryItem accessoryItem = (AccessoryItem) item;
//                return modelMapper.map(accessoryItem, AccessoryItemDto.class);
//            }).toList();
//        }
//        return null;
//    }

    public AccessoryDto getAccessory(Long id) {
        Accessory accessory = accessoryRepo.findById(id).orElse(null);
        if (accessory != null) {
            return modelMapper.map(accessory, AccessoryDto.class);
        }
        return null;
    }

    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(Long accessoryId) {
        List<AccessoryItem> items = accessoryItemRepo.findAccessoryItemsByAccessory_AccessoryId(accessoryId);
        if (items == null) return null;
        return items.stream().map(accessoryItem -> modelMapper.map(accessoryItem, AccessoryItemDto.class)).toList();
    }

    // CREATES
//    @Transactional
//    public AccessoryItemDto newAccessoryItem(AccessoryItemDto accessoryItemDto) {
//        AccessoryItem accessoryItem = modelMapper.map(accessoryItemDto, AccessoryItem.class);
//        Accessory accessory = accessoryItem.getAccessory();
//
//        if (accessory.getAccessoryId() != null) {
//            Accessory foundAccessory = accessoryRepo.findById(accessory.getAccessoryId()).orElse(null);
//            Accessory managedAccessory = entityManager.merge(foundAccessory);
//            accessoryItem.setAccessory(managedAccessory);
//        }
//        AccessoryItem savedAccessoryItem = itemRepo.save(accessoryItem);
//        return modelMapper.map(savedAccessoryItem, AccessoryItemDto.class);
//    }


    public AccessoryDto newAccessory(AccessoryDto accessoryDto) {
        Accessory accessory = modelMapper.map(accessoryDto, Accessory.class);
        Accessory savedAccessory = accessoryRepo.save(accessory);
        //create items
        for (int i = 0; i < accessoryDto.getQuantity(); i++) {
            AccessoryItem accessoryItem = new AccessoryItem();
            accessoryItem.setAccessoryItemStatus(AccessoryItemStatus.AVAILABLE);
            accessoryItem.setAccessory(savedAccessory);
            itemRepo.save(accessoryItem);
        }
        return modelMapper.map(savedAccessory, AccessoryDto.class);
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


    //UPDATES
//    public AccessoryDto updateAccessory(AccessoryDto accessoryDto) {
//        Accessory accessory = modelMapper.map(accessoryDto, Accessory.class);
//        Accessory updatedAccessory = accessoryRepo.save(accessory);
//        return modelMapper.map(updatedAccessory, AccessoryDto.class);
//    }

    @Transactional
    public AccessoryItemDto updateAccessoryItem(AccessoryItemDto accessoryItemDto) {
        AccessoryItem accessoryItem = modelMapper.map(accessoryItemDto, AccessoryItem.class);
        Accessory managedAccessory = entityManager.merge(accessoryItem.getAccessory());
        accessoryItem.setAccessory(managedAccessory);
        return modelMapper.map(itemRepo.save(accessoryItem), AccessoryItemDto.class);
    }


}
