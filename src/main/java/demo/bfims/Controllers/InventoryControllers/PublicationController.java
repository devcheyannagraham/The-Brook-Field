package demo.bfims.Controllers.InventoryControllers;

import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Publication;
import demo.bfims.Services.PublicationItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class PublicationController {
    @Autowired
    PublicationItemService publicationItemService;

    @PostMapping("/newbook")
    public String newBook(@RequestBody Book book) {
        System.out.println();
        System.out.println(book);
        publicationItemService.newPublicationItem(book);
        System.out.println();
        return "New book added";
    }


}
