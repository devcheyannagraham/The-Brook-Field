package demo.bfims.Controllers;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class AccessoryController {

    @Autowired
    AccessoryService accessoryService;

    //Get list of all accessories
    @GetMapping("/accessories")
    public List<AccessoryDto> getAccessories() {
        return accessoryService.getAccessories();
    }

    //Delete accessory
    @DeleteMapping("/accessory/{id}")
    public Response deleteAccessory(@PathVariable Long id) {
        if(id != null) return accessoryService.removeAccessory(id);
        else return null;
    }

    // get specific accessory
    @GetMapping("/accessory/{id}")
    public AccessoryDto newAccessory(@PathVariable Long id) {
        if(id != null) return accessoryService.getAccessory(id);
        else return null;
    }

    //Update Accessory
    @PutMapping("/accessory")
    public AccessoryDto updateAccessory(@RequestBody AccessoryDto accessoryDto) {
        if (accessoryDto != null) return accessoryService.updateAccessory(accessoryDto);
        else return null;
    }

    //Get all accessoryItems (may be useless)
    @GetMapping("/accessoryitems")
    public List<AccessoryItemDto> getAccessoryItems() {
        return accessoryService.getAccessoryItems();
    }

    //Create new accessoryItem. Creates new accessory by default
    @PostMapping("/accessoryitem")
    public AccessoryItemDto newAccessory(@RequestBody AccessoryItemDto accessoryItemDto) {
        if(accessoryItemDto != null) return accessoryService.newAccessoryItem(accessoryItemDto);
        else return null;
    }

    @PostMapping("/accessory")
    public AccessoryDto newAccessory(@RequestBody AccessoryDto accessoryDto) {
        if(accessoryDto != null) return accessoryService.newAccessory(accessoryDto);
        return null;
    }

    //Deleete accessoryitem
    @DeleteMapping("/accessoryitem/{id}")
    public Response deleteAccessoryItem(@PathVariable Long id) {
        if(id != null) return accessoryService.removeAccessoryItem(id);
        else return null;
    }

    //Update accessoryitem
    @PutMapping("/accessoryitem")
    public AccessoryItemDto updateAccessoryItem(@RequestBody AccessoryItemDto accessoryItemDto) {
        if(accessoryItemDto != null) return accessoryService.updateAccessoryItem(accessoryItemDto);
        else return null;
    }

}
