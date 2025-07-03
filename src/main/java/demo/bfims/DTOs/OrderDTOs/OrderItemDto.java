package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Enums.ItemOrderType;
import jakarta.persistence.*;

public class OrderItemDto {

    private Long orderItemId;
    private ItemOrderType itemOrderType;
    private Item item;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public ItemOrderType getItemOrderType() {
        return itemOrderType;
    }

    public void setItemOrderType(ItemOrderType itemOrderType) {
        this.itemOrderType = itemOrderType;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
