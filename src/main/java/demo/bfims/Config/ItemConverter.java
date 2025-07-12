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
public class ItemConverter<S, D> implements Converter<S, D> {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public D convert(MappingContext<S, D> mappingContext) {

        if (ItemDto.class.isAssignableFrom(mappingContext.getSource().getClass())) {
            ItemDto itemDto = (ItemDto) mappingContext.getSource();

//          If item already exists, return it
            Long itemId = itemDto.getItemId();
            if (itemId != null) {
                Item foundItem = itemRepo.findById(itemId).orElse(null);
                if (foundItem != null) {
                    return (D) foundItem;
                }
            }

            ItemType itemType = itemDto.getItemType();
            if (itemType.equals(ItemType.BOOK)) return (D) new Book();
            else if (itemType.equals(ItemType.JOURNAL)) return (D) new Journal();
            else if (itemType.equals(ItemType.LITERARY_PIECE)) return (D) new LiteraryPiece();
        }

        return null;
    }
}
