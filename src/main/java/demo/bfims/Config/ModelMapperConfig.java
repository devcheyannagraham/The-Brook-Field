package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemType;
import demo.bfims.Enums.TransactionType;
import demo.bfims.Repo.ItemRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.Provider;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


//Maps entitys to dtos
@Configuration
public class ModelMapperConfig {

    @Autowired
    ItemRepo itemRepo;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Provider<Item> itemProvider = provisionRequest -> {
            System.out.println("ItemProvider");
            System.out.println(provisionRequest.getSource());
            ItemDto itemDto = (ItemDto) provisionRequest.getSource();

            // If item already exists, return it (Orders)
            Long itemId = itemDto.getItemId();
            if (itemId != null) {
                Item foundItem = itemRepo.findById(itemId).orElse(null);
                if (foundItem != null) {
                    return foundItem;
                }
            }

            if (itemDto.getItemType().equals(ItemType.PUBLICATION_ITEM)) {
                return modelMapper.map(provisionRequest.getSource(), PublicationItem.class);
            }

            else if (itemDto.getItemType().equals(ItemType.ACCESSORY_ITEM)) {
                return modelMapper.map(provisionRequest.getSource(), AccessoryItem.class);
            }
            return null;
        };

        Provider<PublicationItem> publicationItemProvider = provisionRequest -> {
            System.out.println("PublicationItemProvider");
            System.out.println(provisionRequest.getSource());

            PublicationItemDto publicationItemDto = (PublicationItemDto) provisionRequest.getSource();
            PublicationItemType publicationItemType = publicationItemDto.getPublicationItemType();
            System.out.println("publicationItemDto mapped " +  modelMapper.map(provisionRequest.getSource(), Book.class));

            if (publicationItemType.equals(PublicationItemType.BOOK))
                return modelMapper.map(provisionRequest.getSource(), Book.class);
            else if (publicationItemType.equals(PublicationItemType.JOURNAL))
                return modelMapper.map(provisionRequest.getSource(), Journal.class);
            else if (publicationItemType.equals(PublicationItemType.LITERARY_PIECE))
                return modelMapper.map(provisionRequest.getSource(), LiteraryPiece.class);
            return null;
        };

        Provider<Transaction> transactionProvider = provisionRequest -> {
            System.out.println("Transaction Provider");
            System.out.println(provisionRequest.getSource());

            TransactionDto transDto = (TransactionDto) provisionRequest.getSource();
            if (transDto.getTransactionType().equals(TransactionType.RENTAL)) {
                return modelMapper.map(provisionRequest.getSource(), Rental.class);
//
            } else if (transDto.getTransactionType().equals(TransactionType.PURCHASE)) {
                return modelMapper.map(provisionRequest.getSource(), Purchase.class);
            }
            return null;
        };

        TypeMap<ItemDto, Item> itemMap = modelMapper.createTypeMap(ItemDto.class, Item.class);
        TypeMap<PublicationItemDto, PublicationItem> publicationItemMap = modelMapper.createTypeMap(PublicationItemDto.class, PublicationItem.class);
        TypeMap<TransactionDto, Transaction> transactionTypeMap = modelMapper.createTypeMap(TransactionDto.class, Transaction.class);

        itemMap.setProvider(itemProvider);
        publicationItemMap.setProvider(publicationItemProvider);
        transactionTypeMap.setProvider(transactionProvider);


        // VERY IMPORTANT SETTING -> GOT Instantiation Exception even though I had mappings defined because deeply nested properties >(
        modelMapper.getConfiguration().setPreferNestedProperties(false);

        return modelMapper;
    }
}


