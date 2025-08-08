package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.TransactionType;

public class PurchaseDto extends TransactionDto {

    public PurchaseDto() {
        this.setTransactionType(TransactionType.PURCHASE);
    }

    public PurchaseDto(Transaction trans) {
        super(trans);
    }


    @Override
    public String toString() {
        return "PurchaseDto{" +
                "} " + super.toString();
    }
}
