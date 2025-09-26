package demo.bfims.Controllers;

import demo.bfims.Config.Response;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Services.AccessoryService;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
public class AccessoryController {

    AccessoryService accessoryService;
    UserService userService;

    public AccessoryController(AccessoryService accessoryService, UserService userService) {
        this.accessoryService = accessoryService;
        this.userService = userService;
    }

    //Get list of all accessories
    @GetMapping("/accessories")
    public List<AccessoryDto> getAccessories() {
        return accessoryService.getAccessories();
    }

    // get specific accessory
    @GetMapping("/accessory/{accessoryId}")
    public AccessoryDto getAccessoryById(@PathVariable Long accessoryId) {
        if (accessoryId != null)
            return accessoryService.getAccessoryById(accessoryId);
        else return null;
    }

    //get accessory items by acceossry id
    // only returns availabe accessories supposidley
    @GetMapping("/shop/accessory/accessoryitems/{accessoryId}")
    public List<AccessoryItemDto> getAvailableAccessoryItemsByAccessoryId(@PathVariable Long accessoryId) {
        if (accessoryId == null) return null;
        return accessoryService.getAvailableAccessoryItemsByAccessoryId(accessoryId);
    }

    // Requires Admin access
    @GetMapping("/accessory/accessoryitems/{accessoryId}/{uuid}")
    public List<AccessoryItemDto> getAccessoryItemsByAccessoryId(HttpServletRequest request, @PathVariable Long accessoryId, @PathVariable String uuid) {
        if (accessoryId != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return accessoryService.getAccessoryItemsByAccessoryId(accessoryId);
        else return null;
    }

    // Create new accessory
    // Requires Admin access
    @PostMapping("/accessory/{uuid}")
    public AccessoryDto newAccessory(HttpServletRequest request, @RequestBody AccessoryDto accessoryDto, @PathVariable String uuid) {
        if (accessoryDto != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return accessoryService.newAccessory(accessoryDto);
        else return null;
    }

    //Delete accessoryitem
    // Requires Admin access
    @DeleteMapping("/accessoryitem/{id}/{uuid}")
    public Boolean deleteAccessoryItem(HttpServletRequest request, @PathVariable Long id, @PathVariable String uuid) {
        if (id != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return accessoryService.deleteAccessoryItemById(id);
        else return null;
    }

    //Delete 1 accessory
    // Requires Admin access
    @DeleteMapping("/accessory/{id}/{uuid}")
    public Response deleteAccessory(HttpServletRequest request, @PathVariable Long id, @PathVariable String uuid) {
        if (id != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return accessoryService.deleteAccessory(id);
        else return null;
    }
}
