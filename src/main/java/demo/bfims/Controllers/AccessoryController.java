package demo.bfims.Controllers;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccessoryController {

    @Autowired
    AccessoryService accessoryService;

    @GetMapping("/accessories")
    public List<AccessoryItemDto> getAccessoryItems() {
        return accessoryService.getAccessoryItems();
    }

    @PostMapping("/accessory")
    public AccessoryItemDto newAccessory(@RequestBody AccessoryItemDto accessoryItemDto) {
       return accessoryService.newAccessory(accessoryItemDto);
    }

    @DeleteMapping("/accessory/{id}")
    public Response deleteAccessoryItem(@PathVariable Long id) {
        return accessoryService.removeAccessory(id);
    }

    @GetMapping("/accessory/{id}")
    public AccessoryDto newAccessory(@PathVariable Long id) {
       return accessoryService.getAccessory(id);
    }
}
