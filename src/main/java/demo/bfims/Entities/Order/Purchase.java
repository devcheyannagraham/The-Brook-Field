package demo.bfims.Entities.Order;

import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.TransactionType;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
public class Purchase extends Transaction {
    public Purchase() {
        this.setTransactionType(TransactionType.PURCHASE);
    }

    public Purchase(TransactionDto transDto) {
        super(transDto);
    }

    // for bootstrap
    public Purchase(Item item, double price) {
        this.setTransactionType(TransactionType.PURCHASE);
        this.setTransactionDate(LocalDate.now());
        this.setItem(item);
        this.setTransactionPrice(price);

    }
}
