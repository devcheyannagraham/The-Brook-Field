package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends PublicationItem {
    public Book() {
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    public Book(String edition, PublicationItemFormat format, BigDecimal purchasePrice, BigDecimal rentalRate, PublicationItemStatus status, Publication publication) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    public Book(PublicationItemDto bookDto) {
        super(bookDto);
    }

    @Override
    public String toString() {
        return "Book{" +
                "publication=" + publication +
                "} " + super.toString();
    }
}

