package demo.bfims.Controllers;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AccessoryController {

    @Autowired
    AccessoryService accessoryService;

    //Get list of all accessories
    @GetMapping("/accessories")
    public List<AccessoryDto> getAccessories() {
        return accessoryService.getAccessories();
    }

    // get specific accessory
    @GetMapping("/accessory/{accessoryId}")
    public AccessoryDto newAccessory(@PathVariable Long accessoryId) {
        if (accessoryId != null) return accessoryService.getAccessory(accessoryId);
        else return null;
    }

    //get accessory items by acceossry id
    @GetMapping("/accessory/accessoryitems/{accessoryId}")
    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(@PathVariable Long accessoryId) {
        if (accessoryId == null) return null;
        return accessoryService.getAccessoryItemsByAccessoryId(accessoryId);
    }

    //Get all accessoryItems (may be useless)
    @GetMapping("/accessoryitems")
    public List<AccessoryItemDto> getAccessoryItems() {
        return accessoryService.getAccessoryItems();
    }

    //Delete 1 accessory
    @DeleteMapping("/accessory/{id}")
    public Response deleteAccessory(@PathVariable Long id) {
        if (id != null) return accessoryService.removeAccessory(id);
        else return null;
    }

    //Update Accessory // may be useless
    @PutMapping("/accessory")
    public AccessoryDto updateAccessory(@RequestBody AccessoryDto accessoryDto) {
        if (accessoryDto != null) return accessoryService.updateAccessory(accessoryDto);
        else return null;
    }

    //Update accessoryitem
    @PutMapping("/accessoryitem")
    public AccessoryItemDto updateAccessoryItem(@RequestBody AccessoryItemDto accessoryItemDto) {
        if (accessoryItemDto != null) return accessoryService.updateAccessoryItem(accessoryItemDto);
        else return null;
    }

    //Create new accessoryItem. Creates new accessory by default
    @PostMapping("/accessoryitem")
    public AccessoryItemDto newAccessory(@RequestBody AccessoryItemDto accessoryItemDto) {
        if (accessoryItemDto != null) return accessoryService.newAccessoryItem(accessoryItemDto);
        else return null;
    }

    // Create new accessory
    @PostMapping("/accessory")
    public AccessoryDto newAccessory(@RequestBody AccessoryDto accessoryDto) {
        if (accessoryDto != null) return accessoryService.newAccessory(accessoryDto);
        return null;
    }

    //Delete accessoryitem
    @DeleteMapping("/accessoryitem/{id}")
    public Response deleteAccessoryItem(@PathVariable Long id) {
        if (id != null) return accessoryService.removeAccessoryItem(id);
        else return null;
    }
}
