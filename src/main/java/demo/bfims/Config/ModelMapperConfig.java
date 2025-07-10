package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Enums.ItemType;
import org.modelmapper.AbstractProvider;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Maps entitys to dtos
@Configuration
public class ModelMapperConfig {

    @Autowired
    ItemConverter itemConverter;


    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        TypeMap<ItemDto,Item> itemMap = modelMapper.createTypeMap(ItemDto.class,Item.class);
        itemMap.setConverter(itemConverter);
        return modelMapper;
    }
}


