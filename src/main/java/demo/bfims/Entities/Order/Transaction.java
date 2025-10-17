package demo.bfims.Entities.Order;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.DTOs.OrderDTOs.PurchaseDto;
import demo.bfims.DTOs.OrderDTOs.RentalDto;
import demo.bfims.DTOs.OrderDTOs.TransactionDto;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.TransactionType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "transactionType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rental.class, name = "RENTAL"),
        @JsonSubTypes.Type(value = Purchase.class, name = "PURCHASE")
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "transaction_generator")
    @TableGenerator(name = "transaction_generator")
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate transactionDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;
    @ManyToOne()
    @JoinColumn(name = "item_id")
    private Item item;
    private BigDecimal transactionPrice;

    public BigDecimal getTransactionPrice() {
        return transactionPrice;
    }

    public void setTransactionPrice(BigDecimal transactionPrice) {
        this.transactionPrice = transactionPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public Item getItem() {
        return item;
    }

    public Transaction() {
    }

//    Construct Trans from TransDto
    public Transaction(TransactionDto transDto) {
        this.transactionId = transDto.getTransactionId();
        this.transactionType = transDto.getTransactionType();
        this.transactionDate = transDto.getTransactionDate();
        this.setTransactionPrice(transDto.getTransactionPrice());
        //Item is abstract
        this.item = Item.mapToItemSubclass(transDto.getItem());
    }

    //    Trans => PurchaseDto | RentalDto
    public static Transaction mapToTransactionSubclass(TransactionDto transDto) {
        if (transDto instanceof PurchaseDto) return new Purchase(transDto);
        if (transDto instanceof RentalDto) return new Rental(transDto);
        return null;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", transactionType=" + transactionType +
                ", transactionDate=" + transactionDate +
                ", item=" + item +
                ", transactionPrice=" + transactionPrice.doubleValue() +
                '}';
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}
