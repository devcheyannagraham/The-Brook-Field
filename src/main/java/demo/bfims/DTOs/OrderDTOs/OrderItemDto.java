package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Enums.TransactionType;

public class OrderItemDto {

    private Long orderItemId;
    private TransactionType transactionType;
    private ItemDto item;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public TransactionType getItemOrderType() {
        return transactionType;
    }

    public void setItemOrderType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public ItemDto getItem() {
        return item;
    }

    public void setItem(ItemDto item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "OrderItemDto{" +
                "orderItemId=" + orderItemId +
                ", transactionType=" + transactionType +
                ", item=" + item +
                '}';
    }
}
