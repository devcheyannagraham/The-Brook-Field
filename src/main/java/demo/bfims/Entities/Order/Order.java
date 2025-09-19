package demo.bfims.Entities.Order;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.PublicationItemStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    private Customer customer;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime orderDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order")
    private List<Transaction> transactions = new ArrayList<>();
    private Double orderTotal = 0.0;


    public Order() {
        this.transactions = new ArrayList<>();
    }

    public Order(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.customer = new Customer(orderDto.getCustomer());
        this.orderDate = LocalDateTime.now();
        this.orderTotal = orderDto.getOrderTotal();
        this.setTransactions(orderDto.getTransactions().stream()
                .map(Transaction::mapToTransactionSubclass).toList());
    }

    // For bootstrap
    public Order(List<Transaction> trans, Customer customer){
        this.orderDate = LocalDateTime.now();
        trans.forEach(this::addTransaction);
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.orderTotal = 0.0;
        transactions.forEach(this::addTransaction);
    }

    public void addTransaction(Transaction transaction) {
        transaction.setOrder(this);
        this.orderTotal += transaction.getTransactionPrice();

        if (transaction instanceof Purchase purchase) {
            Item item = purchase.getItem();
            if (item instanceof AccessoryItem accessoryItem) {
                accessoryItem.setAccessoryItemStatus(AccessoryItemStatus.PURCHASED);
                transaction.setItem(accessoryItem);
            }
            if (item instanceof PublicationItem publicationItem) {
                publicationItem.setPublicationItemStatus(PublicationItemStatus.PURCHASED);
                transaction.setItem(publicationItem);
            }
        }
        if (transaction instanceof Rental rental) {
            Item item = rental.getItem();
            if (item instanceof PublicationItem publicationItem) {
                publicationItem.setPublicationItemStatus(PublicationItemStatus.RENTED);
                transaction.setItem(publicationItem);
            }
        }

        this.transactions.add(transaction);
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderDate=" + orderDate +
                ", orderItems=" + transactions +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
