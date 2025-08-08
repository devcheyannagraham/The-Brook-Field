package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Entities.Inventory.Publication.Book;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.PublicationItemType;

public class BookDto extends PublicationItemDto {

    public BookDto() {
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    public BookDto(PublicationItem publicationItem) {
        super(publicationItem);
    }

    @Override
    public String toString() {
        return "BookDto{} " + super.toString();
    }
}
