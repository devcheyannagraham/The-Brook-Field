package demo.bfims.Entities.Inventory.Accessory;

import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.AccessoryItemStatus;
import demo.bfims.Enums.ItemType;
import demo.bfims.Interfaces.Purchaseable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AccessoryItem extends Item implements Purchaseable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accessory_id")
    private Accessory accessory;
    private AccessoryItemStatus accessoryItemStatus;

    public AccessoryItem() {
        this.setItemType(ItemType.ACCESSORY_ITEM);
    }

    public AccessoryItemStatus getAccessoryItemStatus() {
        return accessoryItemStatus;
    }

    public void setAccessoryItemStatus(AccessoryItemStatus accessoryItemStatus) {
        this.accessoryItemStatus = accessoryItemStatus;
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
    }

    @Override
    public Double getPurchasePrice() {
        return this.getAccessory().getPrice();
    }

    @Override
    public void setPurchasePrice(Double purchasePrice) {
        this.getAccessory().setPrice(purchasePrice);
    }

    @Override
    public String toString() {
        return "AccessoryItem{" +
                "accessory=" + accessory +
                ", accessoryItemStatus=" + accessoryItemStatus +
                "} " + super.toString();
    }
}
