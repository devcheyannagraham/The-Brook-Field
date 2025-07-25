package demo.bfims.Entities.Inventory.Accessory;

import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Enums.ItemType;
import demo.bfims.Interfaces.Purchaseable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class AccessoryItem extends Item implements Purchaseable {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="accessory_id")
    private Accessory accessory;

    public AccessoryItem() {
        this.setItemType(ItemType.ACCESSORY_ITEM);
    }

    public Accessory getAccessory() {
        return accessory;
    }

    public void setAccessory(Accessory accessory) {
        this.accessory = accessory;
        this.accessory.increaseQuantity();
    }

    @Override
    public Double getPurchasePrice() {
        return this.getAccessory().getPrice();
    }

    @Override
    public void setPurchasePrice(Double purchasePrice) {
        this.getAccessory().setPrice(purchasePrice);

    }
}
