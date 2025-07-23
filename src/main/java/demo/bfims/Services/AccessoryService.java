package demo.bfims.Services;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Repo.ItemRepo;
import jakarta.persistence.EntityManager;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public List<AccessoryItemDto> getAccessoryItems() {
        List<Item> accessoryItems = itemRepo.findItemsByItemType(ItemType.ACCESSORY_ITEM).orElse(null);
        if (accessoryItems != null && !accessoryItems.isEmpty()) {
            return accessoryItems.stream().map(item -> {
                AccessoryItem accessoryItem = (AccessoryItem) item;
                return modelMapper.map(accessoryItem, AccessoryItemDto.class);
            }).toList();
        }
        return null;
    }

    @Transactional
    public AccessoryItemDto newAccessory(AccessoryItemDto accessoryItemDto) {
        System.out.println("newAccessory in service");
        AccessoryItem accessoryItem = modelMapper.map(accessoryItemDto, AccessoryItem.class);
        Accessory accessory = accessoryItem.getAccessory();

        if (accessory.getAccessoryId() != null) {
            Accessory foundAccessory = accessoryRepo.findById(accessory.getAccessoryId()).orElse(null);
            Accessory managedAccessory = entityManager.merge(foundAccessory);
            accessoryItem.setAccessory(managedAccessory);
        }
        AccessoryItem savedAccessoryItem = itemRepo.save(accessoryItem);
        return modelMapper.map(savedAccessoryItem, AccessoryItemDto.class);
    }

    public AccessoryDto getAccessory(Long id) {
        Accessory accessory = accessoryRepo.findById(id).orElse(null);
        if (accessory != null) {
            return modelMapper.map(accessory, AccessoryDto.class);
        }
        return null;
    }

    @Transactional
    public Response removeAccessory(Long id) {
        Response response = new Response();
        Integer rows = accessoryRepo.deleteByAccessoryId(id);
        response.getMessages().put("success","Accessory has been removed successfully");
        response.getMessages().put("rows effected",rows.toString());
        return response;
    }

    public AccessoryDto updateAccessory(AccessoryDto accessoryDto) {
        Accessory accessory = modelMapper.map(accessoryDto, Accessory.class);
        Accessory updatedAccessory = accessoryRepo.save(accessory);
        return modelMapper.map(updatedAccessory, AccessoryDto.class);

    }

}
