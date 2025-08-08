package demo.bfims.DTOs.OrderDTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.Entities.Order.Purchase;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Entities.Order.Transaction;
import demo.bfims.Enums.TransactionType;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "transactionType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RentalDto.class, name = "RENTAL"),
        @JsonSubTypes.Type(value = PurchaseDto.class, name = "PURCHASE")
})
public class TransactionDto {
    private Long transactionId;
    private TransactionType transactionType;
    private LocalDate transactionDate;
    private ItemDto item;
    private Double transactionPrice;

    public TransactionDto() {
    }

//    Construct TransDto from Trans
    public TransactionDto(Transaction trans) {
        this.transactionId = trans.getTransactionId();
        this.transactionType = trans.getTransactionType();
        this.transactionDate = trans.getTransactionDate();
        this.transactionPrice = trans.getTransactionPrice();
        this.item = ItemDto.mapToItemDtoSubclass(trans.getItem());
    }

    //TransDto => Purchase | Rental
    public static TransactionDto mapToTransactionDtoSubclass(Transaction trans) {
        if (trans instanceof Purchase) return new PurchaseDto(trans);
        if (trans instanceof Rental) return new RentalDto(trans);
        return null;
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
