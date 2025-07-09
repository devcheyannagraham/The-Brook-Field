package demo.bfims.Entities.Order;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Journal;
import demo.bfims.Entities.Inventory.LiteraryPiece;
import demo.bfims.Enums.ItemOrderType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "transactionType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Rental.class, name = "RENTAL"),
        @JsonSubTypes.Type(value = Purchase.class, name = "PURCHASE")
})
@MappedSuperclass
public abstract class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "transaction_generator")
    @TableGenerator(name = "transaction_generator")
    private Long transactionId;
    @Enumerated(EnumType.STRING)
    private ItemOrderType transactionType;
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate transactionDate;
    @ManyToOne(cascade = CascadeType.MERGE)
    private Order order;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="order_item_id")
    private OrderItem orderItem;

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

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public ItemOrderType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(ItemOrderType transactionType) {
        this.transactionType = transactionType;
    }
}
