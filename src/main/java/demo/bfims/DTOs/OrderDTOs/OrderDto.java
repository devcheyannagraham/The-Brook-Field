package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Order.Order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private CustomerDto customer;
    private List<TransactionDto> transactions = new ArrayList<>();
    private Double orderTotal;
    private LocalDateTime orderDate;


    public OrderDto() {
    }

    public OrderDto(Order order) {
        this.id = order.getId();
        this.customer = new CustomerDto(order.getCustomer());
        this.orderTotal = order.getOrderTotal();
        this.orderDate = order.getOrderDate();
        this.transactions = order.getTransactions().stream()
                .map(TransactionDto::mapToTransactionDtoSubclass).toList();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public void setTransactions(List<TransactionDto> transactions) {
        this.transactions = transactions;
    }

    public List<TransactionDto> getTransactions() {
        return transactions;
    }

    public void setOrderItems(List<TransactionDto> orderItems) {
        this.transactions = orderItems;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customer=" + customer +
                ", transactions=" + transactions +
                ", orderTotal=" + orderTotal +
                ", orderDate=" + orderDate +
                '}';
    }
}
