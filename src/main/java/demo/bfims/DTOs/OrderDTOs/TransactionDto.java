package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Enums.TransactionType;

import java.time.LocalDate;

public class TransactionDto {
    private Long transactionId;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private ItemDto item;
    private Double transactionPrice;

    public TransactionDto() {
    }

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

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    public Double getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(Double transactionPrice) {
        this.transactionPrice = transactionPrice;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                ", item=" + item +
                ", transactionPrice=" + transactionPrice +
                '}';
    }
}
