package demo.bfims.DTOs.InventoryDTOs;

import demo.bfims.Enums.ItemType;

public class ItemDto {
    private Long itemId;
    private ItemType itemType;

    public ItemDto() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                "itemId=" + itemId +
                ", itemType=" + itemType +
                '}';
    }
}
