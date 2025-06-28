package demo.bfims.Controllers.InventoryControllers;

import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Publication;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Services.PublicationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicationController {
    @Autowired
    PublicationItemService publicationItemService;

    @PostMapping("/newpublicationitem")
    public String newBook(@RequestBody PublicationItem publicationItem) {
        System.out.println();
        System.out.println(publicationItem.getClass().getSimpleName());
        System.out.println(publicationItem);
        publicationItemService.newPublicationItem(publicationItem);
        System.out.println();
        return "New publicationItem added";
    }

    @PostMapping("/updatebook")
    public String updateBook(@RequestBody Book book) {
        System.out.println();
        System.out.println(book);
        publicationItemService.updatePublicationItem(book);
        System.out.println();
        return "New book added";
    }


}
