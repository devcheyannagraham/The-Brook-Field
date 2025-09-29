package demo.bfims.DTOs.InventoryDTOs.Accessory;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Enums.AccessoryType;

public class AccessoryDto {
    private Long accessoryId;
    private AccessoryType accessoryType;
    private String accessoryName;
    private double price;
    // needed to create items on new accessory
    private int quantity;
    private SVGIcon svgIcon;

    public AccessoryDto() {
    }

    public AccessoryDto(Accessory accessory) {
        this.accessoryId = accessory.getAccessoryId();
        this.accessoryType = accessory.getAccessoryType();
        this.accessoryName = accessory.getAccessoryName();
        this.price = accessory.getPrice();
        this.svgIcon = accessory.getSvgIcon();
    }

    public Long getAccessoryId() {
        return accessoryId;
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        this.accessoryType = accessoryType;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "AccessoryDto{" +
                "accessoryId=" + accessoryId +
                ", accessoryType=" + accessoryType +
                ", accessoryName='" + accessoryName + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", svgIcon=" + svgIcon +
                '}';
    }
}
