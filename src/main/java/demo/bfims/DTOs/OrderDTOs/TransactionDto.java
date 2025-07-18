package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Enums.TransactionType;

import java.time.LocalDate;

public class TransactionDto {
    private Long transactionId;
    private TransactionType transactionType;
    private LocalDate transactionDate;
//    private Order order;
    private Item item;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

//    public Order getOrder() {
//        return order;
//    }
//
//    public void setOrder(Order order) {
//        this.order = order;
//    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
//                ", order=" + order +
                ", item=" + item +
                '}';
    }
}
