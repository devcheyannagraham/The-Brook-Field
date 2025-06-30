package demo.bfims.Controllers.InventoryControllers;

import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Services.PublicationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicationController {
    @Autowired
    PublicationItemService publicationItemService;

    @PostMapping("/publicationitem")
    public PublicationItemDto newPublicationItem(@RequestBody PublicationItem publicationItem) {
        return publicationItemService.newPublicationItem(publicationItem);
    }

    @GetMapping("/publicationitem/{id}")
    public PublicationItemDto getPublicationItem(@PathVariable Long id){
        return publicationItemService.getPublicationItem(id);
    }

    @GetMapping("/publicationitems")
    public List<PublicationItemDto> getPublicationItems() {
        return publicationItemService.getPublicationItems();
    }

    @DeleteMapping("/publicationitem/delete/{id}")
    public Boolean deletePublicationItem(@PathVariable Long id) {
        return publicationItemService.deletePublicationItem(id);
    }
}
