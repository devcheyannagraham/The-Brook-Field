package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Order.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {
    private Long id;
    private CustomerDto customer;
    private List<OrderItemDto> orderItems = new ArrayList<>();
    private Double orderTotal;

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

    public List<OrderItemDto> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItemDto> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Double orderTotal) {
        this.orderTotal = orderTotal;
    }


    @Override
    public String toString() {
        return "OrderDto{" +
                "id=" + id +
                ", customer=" + customer +
                ", orderItems=" + orderItems +
                ", orderTotal=" + orderTotal +
                '}';
    }
}
