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
    // only returns availabe accessories supposidley
    @GetMapping("/shop/accessory/accessoryitems/{accessoryId}")
    public List<AccessoryItemDto> getAvailableAccessoryItemsByAccessoryId(@PathVariable Long accessoryId) {
        if (accessoryId == null) return null;
        return accessoryService.getAvailableAccessoryItemsByAccessoryId(accessoryId);
    }

    @GetMapping("/accessory/accessoryitems/{accessoryId}")
    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(@PathVariable Long accessoryId) {
        if (accessoryId == null) return null;
        return accessoryService.getAccessoryItemsByAccessoryId(accessoryId);
    }

    //Update accessoryitem // may delete
    @PutMapping("/accessoryitem")
    public AccessoryItemDto updateAccessoryItem(@RequestBody AccessoryItemDto accessoryItemDto) {
        if (accessoryItemDto != null) return accessoryService.updateAccessoryItem(accessoryItemDto);
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
    public Boolean deleteAccessoryItem(@PathVariable Long id) {
        if (id != null) return accessoryService.deleteAccessoryItemById(id);
        else return null;
    }

    //Delete 1 accessory
    @DeleteMapping("/accessory/{id}")
    public Response deleteAccessory(@PathVariable Long id) {
        if (id != null) return accessoryService.deleteAccessory(id);
        else return null;
    }
}
