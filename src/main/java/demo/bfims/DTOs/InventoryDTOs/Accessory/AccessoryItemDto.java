package demo.bfims.DTOs.InventoryDTOs.Accessory;

import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ItemType;

public class AccessoryItemDto extends ItemDto {
    private Accessory accessory;
    private AccessoryItemStatus accessoryItemStatus;

    public Accessory getAccessory() {
        return accessory;
    }

    public AccessoryItemStatus getAccessoryItemStatus() {
        return accessoryItemStatus;
    }

    public void setAccessoryItemStatus(AccessoryItemStatus accessoryItemStatus) {
        this.accessoryItemStatus = accessoryItemStatus;
    }

    public AccessoryItemDto() {
        this.setItemType(ItemType.ACCESSORY_ITEM);
    }

    @Override
    public String toString() {
        return "AccessoryItemDto{" +
                "accessory=" + accessory +
                ", accessoryItemStatus=" + accessoryItemStatus +
                '}';
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }
}
