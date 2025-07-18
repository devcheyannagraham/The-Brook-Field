package demo.bfims.Config;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.OrderDTOs.RentalDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.TransactionType;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class TransactionConverter implements Converter<TransactionDto, Transaction> {

    private final ItemConverter<ItemDto, Item> itemConverter;

    public TransactionConverter(ItemConverter<ItemDto, Item> itemConverter) {
        this.itemConverter = itemConverter;
    }

    @Override
    public Transaction convert(MappingContext<TransactionDto, Transaction> mappingContext) {
        TransactionDto transDto = mappingContext.getSource();
        System.out.println("dto: " + transDto);
        Transaction trans = null;

        if (transDto.getTransactionType().equals(TransactionType.RENTAL)) {
            Rental rental = new Rental();
            rental.setTransactionType(TransactionType.RENTAL);
            trans = rental;
        } else if (transDto.getTransactionType().equals(TransactionType.PURCHASE)) {
            Purchase purchase = new Purchase();
            purchase.setTransactionType(TransactionType.PURCHASE);
            trans = purchase;
        }
        Item convertedItem = itemConverter.convert(mappingContext.create(transDto.getItem(), Item.class));
        trans.setItem(convertedItem);
        return trans;
    }
}
