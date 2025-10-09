package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Services.PublicationService;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
public class PublicationController {
    PublicationService publicationService;
    UserService userService;

    public PublicationController(PublicationService publicationService, UserService userService) {
        this.publicationService = publicationService;
        this.userService = userService;
    }

    //get pubItems for a pub
    @GetMapping("/publicationitems/{pubId}")
    public List<PublicationItemDto> getPublicationItemsByPublicationId(HttpServletRequest request, @PathVariable Long pubId, @RequestHeader("user-uuid") String uuid) {
        if (pubId != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return publicationService.getPublicationItemsByPublicationId(pubId);
        else return null;
    }

    //only get available pubItems for a pub
    @GetMapping("/shop/publicationitems/{pubId}")
    public List<PublicationItemDto> getAvailablePublicationItemsByPublicationId(@PathVariable Long pubId) {
        if (pubId == null) return null;
        return publicationService.getAvailablePublicationItemsByPublicationId(pubId);
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
        return publicationService.getPublicationById(id);
    }

    //Get 1 pubitem
    @GetMapping("/publicationitem/{id}")
    public PublicationItemDto getPublicationItemById(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @PathVariable Long id) {
        if (id != null && uuid != null && this.userService.isSessionUserAdmin(request, uuid))
            return publicationService.getPublicationItemById(id);
        else return null;
    }

    // new PublicationItem
    @PostMapping("/publicationitem")
    public List<PublicationItemDto> newPublicationItem(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @RequestBody PublicationItemDto publicationItemDto) {
        if (uuid != null && publicationItemDto != null && this.userService.isSessionUserAdmin(request, uuid)) {
            List<PublicationItemDto> newItems = new ArrayList<>();

            //updating single item
            if (publicationItemDto.getQuantity() == null) {
                newItems.add(publicationService.newPublicationItem(publicationItemDto));
            } else {
                // Creating multiple or single new items of same type
                int quantity = publicationItemDto.getQuantity();
                for (int i = 0; i < quantity; i++) {
                    newItems.add(publicationService.newPublicationItem(publicationItemDto));
                }
            }
            return newItems;
        } else return null;
    }

    // New Publication
    @PostMapping("/publication")
    public PublicationDto newPublication(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @RequestBody PublicationDto publicationDto) {
        if (uuid != null && publicationDto != null && this.userService.isSessionUserAdmin(request, uuid))
            return publicationService.newPublication(publicationDto);
        else return null;
    }


    // delete 1 pub item
    @DeleteMapping("/publicationitem/{id}")
    public Boolean deletePublicationItem(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @PathVariable Long id) {
        if (uuid != null && id != null && this.userService.isSessionUserAdmin(request, uuid))
            return publicationService.deletePublicationItem(id);
        else return false;
    }

    @DeleteMapping("/publication/{id}")
    public Boolean deletePublicationById(HttpServletRequest request, @RequestHeader("user-uuid") String uuid, @PathVariable Long id) {
        if (uuid != null && id != null && this.userService.isSessionUserAdmin(request, uuid))
            return publicationService.deletePublicationById(id);
        else return false;
    }
}
