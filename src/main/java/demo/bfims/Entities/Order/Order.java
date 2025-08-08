package demo.bfims.Entities.Order;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Enums.TransactionType;
import demo.bfims.Interfaces.Purchaseable;
import demo.bfims.Interfaces.Rentable;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

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
    private List<Transaction> transactions;
    private Double orderTotal;


    public Order() {
        this.transactions = new ArrayList<>();
    }

    public Order(OrderDto orderDto) {
        this.id = orderDto.getId();
        this.customer = new Customer(orderDto.getCustomer());
        this.orderDate = LocalDateTime.now();
        this.orderTotal = orderDto.getOrderTotal();
        this.transactions = orderDto.getTransactions().stream()
                .map(Transaction::mapToTransactionSubclass).toList();
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
        this.transactions.add(transaction);
        this.orderTotal += transaction.getTransactionPrice();
        if (transaction.getTransactionType().equals(TransactionType.PURCHASE)) {
            transaction.setTransactionPrice(((Purchaseable) transaction.getItem()).getPurchasePrice());
        }
        if (transaction.getTransactionType().equals(TransactionType.RENTAL)) {
            transaction.setTransactionPrice(((Rentable) transaction.getItem()).getRentalRate());
        }
        transaction.setOrder(this);
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
