package demo.bfims.DTOs.InventoryDTOs.Accessory;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Enums.AccessoryType;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AccessoryDto {
    private Long accessoryId;
    private AccessoryType accessoryType;
    private String accessoryName;
    private BigDecimal price;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price.setScale(2,RoundingMode.HALF_UP);
    }

    public void setPrice(Double price) {
        this.price = BigDecimal.valueOf(price).setScale(2, RoundingMode.HALF_UP);
    }

    public SVGIcon getSvgIcon() {
        return svgIcon;
    }

    public void setSvgIcon(SVGIcon svgIcon) {
        this.svgIcon = svgIcon;
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
