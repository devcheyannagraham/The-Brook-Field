package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.BookDto;
import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.ItemType;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;

public class ItemConverter implements Converter<ItemDto, Item> {
    @Override
    public Item convert(MappingContext<ItemDto, Item> context) {
        ItemType itemType = context.getSource().getItemType();

        if(itemType.equals(ItemType.BOOK)) return new Book();
        else if (itemType.equals(ItemType.JOURNAL)) return new Journal();
        else if (itemType.equals(ItemType.LITERARY_PIECE)) return new LiteraryPiece();

        return null;
    }
}
