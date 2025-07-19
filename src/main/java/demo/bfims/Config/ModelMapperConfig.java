package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationItemDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.*;
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
                return (PublicationItem) provisionRequest.getSource();
            }
            return null;
        };

        Provider<PublicationItem> publicationItemProvider = provisionRequest -> {
            System.out.println("PublicationItemProvider");
            System.out.println(provisionRequest.getSource());

            PublicationItemDto publicationItemDto = (PublicationItemDto) provisionRequest.getSource();
            PublicationItemType publicationItemType = publicationItemDto.getPublicationItemType();

            if (publicationItemType.equals(PublicationItemType.BOOK))
                return (Book) provisionRequest.getSource();
            else if (publicationItemType.equals(PublicationItemType.JOURNAL))
                return (Journal) provisionRequest.getSource();
            else if (publicationItemType.equals(PublicationItemType.LITERARY_PIECE))
                return (LiteraryPiece) provisionRequest.getSource();
            return null;
        };

        Provider<Transaction> transactionProvider = provisionRequest -> {
            System.out.println("Transaction Provider");
            System.out.println(provisionRequest.getSource());
            TransactionDto transDto = (TransactionDto) provisionRequest.getSource();
            Item item = modelMapper.map(transDto.getItem(), Item.class);
            System.out.println("\nCONVERTED ITEM " + item);

            if (transDto.getTransactionType().equals(TransactionType.RENTAL)) {
                Rental rental = new Rental();
                rental.setTransactionType(TransactionType.RENTAL);
                rental.setItem(item);
                System.out.println("HERE IN RENTAL");
                return (Transaction) rental;

            } else if (transDto.getTransactionType().equals(TransactionType.PURCHASE)) {
                Purchase purchase = new Purchase();
                purchase.setTransactionType(TransactionType.PURCHASE);
                purchase.setItem(item);
                System.out.println("HERE IN PURCHASE");
                return (Transaction) purchase;
            }
            return null;
        };

        TypeMap<ItemDto, Item> itemMap = modelMapper.createTypeMap(ItemDto.class, Item.class);
        TypeMap<PublicationItemDto, PublicationItem> publicationItemMap = modelMapper.createTypeMap(PublicationItemDto.class, PublicationItem.class);
        TypeMap<TransactionDto, Transaction> transactionTypeMap = modelMapper.createTypeMap(TransactionDto.class, Transaction.class);

        itemMap.setProvider(itemProvider);
        publicationItemMap.setProvider(publicationItemProvider);
        transactionTypeMap.setProvider(transactionProvider);

        return modelMapper;
    }
}


