package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Services.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PublicationController {
    PublicationService publicationService;

    public PublicationController(PublicationService publicationService) {
        this.publicationService = publicationService;
    }

    // new PublicationItem
    @PostMapping("/publicationitem")
    public List<PublicationItemDto> newPublicationItem(@RequestBody PublicationItemDto publicationItemDto) {
        System.out.println("PublicationItemDto = " + publicationItemDto);
        if (publicationItemDto == null) {
            return null;
        }
        List<PublicationItemDto> newItems = new ArrayList<>();

        // Make multiple items of same type
        int quantity = publicationItemDto.getQuantity();
        for (int i = 0; i < quantity; i++) {
            newItems.add(publicationService.newPublicationItem(publicationItemDto));
        }
        return newItems;
    }

    @PostMapping("/publication")
    public PublicationDto newPublication(@RequestBody PublicationDto publicationDto) {
        if (publicationDto == null) {
            return null;
        }
        return publicationService.newPublication(publicationDto);

    }

    //Get 1 pubitem
    @GetMapping("/publicationitem/{id}")
    public PublicationItemDto getPublicationItem(@PathVariable Long id) {
        return publicationService.getPublicationItem(id);
    }

    //get all pubitems
    @GetMapping("/publicationitems")
    public List<PublicationItemDto> getPublicationItems() {
        return publicationService.getPublicationItems();
    }

    //get pubItems for a pub
    @GetMapping("/publicationitems/{pubId}")
    public List<PublicationItemDto> getPublicationItemsByPublicationId(@PathVariable Long pubId) {
        if (pubId == null) return null;
        return publicationService.getPublicationItemsByPublicationId(pubId);
    }

    // delete 1 pub item
    @DeleteMapping("/publicationitem/delete/{id}")
    public Boolean deletePublicationItem(@PathVariable Long id) {
        return publicationService.deletePublicationItem(id);
    }

    //get all publications
    @GetMapping("/publications")
    public List<PublicationDto> getPublications() {
        return publicationService.getPublications();
    }

    //get 1 pub
    @GetMapping("/publication/{id}")
    public PublicationDto getPublicationById(@PathVariable Long id) {
        if (id == null) {
            return null;
        }
        return publicationService.getPublication(id);
    }

}
