package demo.bfims.Entities.Order;

import demo.bfims.DTOs.OrderDTOs.PurchaseDto;
import demo.bfims.Enums.TransactionType;
import jakarta.persistence.*;


@Entity
public class Purchase extends Transaction {
    public Purchase() {
        this.setTransactionType(TransactionType.PURCHASE);
    }

    public Purchase(PurchaseDto purchaseDto) {
        super(purchaseDto);
    }
}
