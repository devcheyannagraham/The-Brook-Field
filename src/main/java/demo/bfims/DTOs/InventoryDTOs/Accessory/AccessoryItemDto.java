package demo.bfims.DTOs.InventoryDTOs.Accessory;

import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ItemType;

public class AccessoryItemDto extends ItemDto {
    private AccessoryDto accessory;
    private AccessoryItemStatus accessoryItemStatus;


    public AccessoryItemDto() {
        this.setItemType(ItemType.ACCESSORY_ITEM);
    }

    public AccessoryItemDto(Item item) {
        super(item);
        if (item instanceof AccessoryItem accessoryItem) {
            this.accessoryItemStatus = accessoryItem.getAccessoryItemStatus();
            this.accessory = new AccessoryDto(accessoryItem.getAccessory());
        }
    }

    public AccessoryDto getAccessory() {
        return accessory;
    }

    public AccessoryItemStatus getAccessoryItemStatus() {
        return accessoryItemStatus;
    }

    public void setAccessoryItemStatus(AccessoryItemStatus accessoryItemStatus) {
        this.accessoryItemStatus = accessoryItemStatus;
    }


    public void setAccessory(AccessoryDto accessory) {
        this.accessory = accessory;
    }

    @Override
    public String toString() {
        return "AccessoryItemDto{" +
                "accessory=" + accessory +
                ", accessoryItemStatus=" + accessoryItemStatus +
                '}';
    }
}
