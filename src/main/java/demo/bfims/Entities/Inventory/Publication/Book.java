package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.DTOs.InventoryDTOs.Publication.BookDto;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
//REmove
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends PublicationItem {
    public Book() {
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    public Book(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus status, Publication publication) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    public Book(BookDto bookDto) {
        super(bookDto); //PublicationItem
    }

    @Override
    public String toString() {
        return "Book{" +
                "publication=" + publication +
                "} " + super.toString();
    }
}

