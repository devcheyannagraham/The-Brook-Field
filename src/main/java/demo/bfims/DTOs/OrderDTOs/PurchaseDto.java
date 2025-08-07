package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Enums.TransactionType;

public class PurchaseDto extends TransactionDto {

    public PurchaseDto() {
        this.setTransactionType(TransactionType.PURCHASE);
    }

    public PurchaseDto(Purchase purchase) {
        super(purchase);
    }


    @Override
    public String toString() {
        return "PurchaseDto{" +
                "} " + super.toString();
    }
}
