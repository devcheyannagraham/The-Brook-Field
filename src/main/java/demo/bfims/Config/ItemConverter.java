package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemType;
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

        if (itemType.equals(ItemType.PUBLICATION_ITEM)) {
            PublicationItemDto publicationItemDto = (PublicationItemDto) mappingContext.getSource();
            PublicationItemType publicationItemType = publicationItemDto.getPublicationItemType();

            if (publicationItemType.equals(PublicationItemType.BOOK)) return (D) modelMapper.map(itemDto, Book.class);
            else if (publicationItemType.equals(PublicationItemType.JOURNAL))
                return (D) modelMapper.map(itemDto, Journal.class);
            else if (publicationItemType.equals(PublicationItemType.LITERARY_PIECE))
                return (D) modelMapper.map(itemDto, LiteraryPiece.class);
        }
        return null;
    }
}
