package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Journal;
import demo.bfims.Entities.Inventory.LiteraryPiece;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Enums.ItemType;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class PublicationItemConverter implements Converter<PublicationItemDto, PublicationItem> {

    @Override
    public PublicationItem convert(MappingContext<PublicationItemDto, PublicationItem> context) {
        ItemType itemType = context.getSource().getItemType();

        if(itemType.equals(ItemType.BOOK)) return new Book();
        else if (itemType.equals(ItemType.JOURNAL)) return new Journal();
        else if (itemType.equals(ItemType.LITERARY_PIECE)) return new LiteraryPiece();

        return null;
    }
}
