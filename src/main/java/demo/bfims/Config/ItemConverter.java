package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Repo.ItemRepo;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


//map itemdto to item child since item is abstract
@Component
public class ItemConverter<S extends ItemDto, D extends Item> implements Converter<S, D> {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public D convert(MappingContext<S, D> mappingContext) {

        mappingContext.getSource();
        ItemDto itemDto = (ItemDto) mappingContext.getSource();

        // If item already exists, return it
        Long itemId = itemDto.getItemId();
        if (itemId != null) {
            Item foundItem = itemRepo.findById(itemId).orElse(null);
            if (foundItem != null) {
                return (D) foundItem;
            }
        }

        ItemType itemType = itemDto.getItemType();
        ModelMapper modelMapper = new ModelMapper();

        if (itemType.equals(ItemType.BOOK)) return (D)  modelMapper.map(itemDto, Book.class);
        else if (itemType.equals(ItemType.JOURNAL)) return (D) modelMapper.map(itemDto, Journal.class);
        else if (itemType.equals(ItemType.LITERARY_PIECE)) return (D) modelMapper.map(itemDto, LiteraryPiece.class);
        return null;
    }
}
