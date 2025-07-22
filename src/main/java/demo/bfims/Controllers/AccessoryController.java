package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Repo.AccessoryRepo;
import demo.bfims.Services.AccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AccessoryController {

    @Autowired
    AccessoryService accessoryService;

    @PostMapping("/accessory")
    public AccessoryItemDto newAccessory(@RequestBody AccessoryItemDto accessoryItemDto) {
       return accessoryService.newAccessory(accessoryItemDto);
    }
}
