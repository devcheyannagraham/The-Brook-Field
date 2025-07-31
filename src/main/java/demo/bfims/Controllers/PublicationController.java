package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Services.PublicationItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationController {
    PublicationItemService publicationItemService;

    public PublicationController(PublicationItemService publicationItemService) {
        this.publicationItemService = publicationItemService;
    }

    // new PublicationItem (with new pub)
    @PostMapping("/publicationitem")
    public PublicationItemDto newPublicationItem(@RequestBody PublicationItemDto publicationItemDto) {
        if (publicationItemDto == null) {
            return null;
        }
        return publicationItemService.newPublicationItem(publicationItemDto);
    }

    //Get 1 pubitem
    @GetMapping("/publicationitem/{id}")
    public PublicationItemDto getPublicationItem(@PathVariable Long id) {
        return publicationItemService.getPublicationItem(id);
    }

    //get all pubitems
    @GetMapping("/publicationitems")
    public List<PublicationItemDto> getPublicationItems() {
        return publicationItemService.getPublicationItems();
    }

    //get pubItems for a pub
    @GetMapping("/publicationitems/{pubId}")
    public List<PublicationItemDto> getPublicationItemsByPublicationId(@PathVariable Long pubId) {
        if(pubId == null) return null;
        return publicationItemService.getPublicationItemsByPublicationId(pubId);
    }

    // delete 1 pub item
    @DeleteMapping("/publicationitem/delete/{id}")
    public Boolean deletePublicationItem(@PathVariable Long id) {
        return publicationItemService.deletePublicationItem(id);
    }

    //get all publications
    @GetMapping("/publications")
    public List<PublicationDto> getPublications() {
        return publicationItemService.getPublications();
    }

    //get 1 pub
    @GetMapping("/publication/{id}")
    public PublicationDto getPublicationById(@PathVariable Long id) {
        if (id == null) {
            return null;
        }
        return publicationItemService.getPublication(id);
    }

}
