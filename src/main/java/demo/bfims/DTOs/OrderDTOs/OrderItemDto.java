package demo.bfims.DTOs.OrderDTOs;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Enums.ItemOrderType;

public class OrderItemDto {

    private Long orderItemId;
    private ItemOrderType itemOrderType;
    private ItemDto item;

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
                ", itemOrderType=" + itemOrderType +
                ", item=" + item +
                '}';
    }
}
