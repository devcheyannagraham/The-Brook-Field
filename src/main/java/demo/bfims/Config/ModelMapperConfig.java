package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.PublicationItem;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Maps entitys to dtos
@Configuration
public class ModelMapperConfig {

    @Autowired
    ItemConverter itemConverter;

    @Autowired
    PublicationItemConverter publicationItemConverter;


    @Bean
    public ModelMapper modelMapper() {
        // tell model mmapper to use custom converter for items
        // item is abstract and can't be instantiated so requires
        // special configuration
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ItemDto,Item> itemMap = modelMapper.createTypeMap(ItemDto.class,Item.class);
        itemMap.setConverter(itemConverter);

        TypeMap<PublicationItemDto, PublicationItem> publicationItemMap = modelMapper.createTypeMap(PublicationItemDto.class, PublicationItem.class);
        publicationItemMap.setConverter(publicationItemConverter);
        return modelMapper;
    }
}


