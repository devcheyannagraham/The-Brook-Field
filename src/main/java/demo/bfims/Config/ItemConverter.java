package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.ItemRepo;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



//map itemdto to item child since item is abstract
@Component
public class ItemConverter implements Converter<ItemDto, Item> {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public Item convert(MappingContext<ItemDto, Item> context) {

        // If item already exists, return it
        Long itemId = context.getSource().getItemId();
        if (itemId != null) {
            Item foundItem = itemRepo.findById(itemId).orElse(null);
            if (foundItem != null) {
                return foundItem;
            }
        }

        // else return subclass
        ItemType itemType = context.getSource().getItemType();

        if(itemType.equals(ItemType.BOOK)) return new Book();
        else if (itemType.equals(ItemType.JOURNAL)) return new Journal();
        else if (itemType.equals(ItemType.LITERARY_PIECE)) return new LiteraryPiece();

        return null;
    }
}
