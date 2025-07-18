package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Entities.Order.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Maps entitys to dtos
@Configuration
public class ModelMapperConfig {

    @Autowired
    ItemConverter<ItemDto,Item> itemConverter;

    @Autowired
    ItemConverter<PublicationItemDto,PublicationItem> publicationItemConverter;

    @Autowired
    TransactionConverter transactionConverter;

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

        TypeMap<TransactionDto, Transaction> transactionMap = modelMapper.createTypeMap(TransactionDto.class,Transaction.class);
        transactionMap.setConverter(transactionConverter);
        return modelMapper;
    }
}


