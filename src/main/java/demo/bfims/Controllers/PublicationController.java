package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Services.PublicationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PublicationController {
    @Autowired
    PublicationItemService publicationItemService;

    @PostMapping("/publicationitem")
    public PublicationItemDto newPublicationItem(@RequestBody PublicationItemDto publicationItemDto) {
        return publicationItemService.newPublicationItem(publicationItemDto);
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

    @GetMapping("/publications")
    public List<PublicationDto> getPublications(){
        return publicationItemService.getPublications();
    }
}
