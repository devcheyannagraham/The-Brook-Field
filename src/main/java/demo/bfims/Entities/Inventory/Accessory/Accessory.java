package demo.bfims.Entities.Inventory.Accessory;


import demo.bfims.Config.SVGIcon;
import demo.bfims.Config.SVGIconFactory;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.Enums.AccessoryType;
import jakarta.persistence.*;

@Entity
public class Accessory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accessoryId;
    @Enumerated(EnumType.STRING)
    private AccessoryType accessoryType;
    private String accessoryName;
    private double price;
    @Embedded
    private SVGIcon svgIcon;

    public Long getAccessoryId() {
        return accessoryId;
    }

    public Accessory(String accessoryName, AccessoryType accessoryType, double price) {
        this.setAccessoryType(accessoryType);
        this.accessoryName = accessoryName;
        this.price = price;
    }

    public Accessory() {
    }

    public Accessory(AccessoryDto accessoryDto) {
        this.svgIcon = accessoryDto.getSvgIcon();
        this.accessoryName = accessoryDto.getAccessoryName();
        this.price = accessoryDto.getPrice();
        this.accessoryId = accessoryDto.getAccessoryId();
        this.setAccessoryType(accessoryDto.getAccessoryType());
    }

    public void setAccessoryId(Long accessoryId) {
        this.accessoryId = accessoryId;
    }

    public AccessoryType getAccessoryType() {
        return accessoryType;
    }

    public void setAccessoryType(AccessoryType accessoryType) {
        if (this.accessoryType == null || !(this.accessoryType.equals(accessoryType))) {
            this.svgIcon = SVGIconFactory.CreateAccessoryItemIcon(accessoryType);
        }
        this.accessoryType = accessoryType;
    }

    public String getAccessoryName() {
        return accessoryName;
    }

    public void setAccessoryName(String accessoryName) {
        this.accessoryName = accessoryName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SVGIcon getSvgIcon() {
        return svgIcon;
    }

    public void setSvgIcon(SVGIcon icon) {
        this.svgIcon = icon;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "accessoryId=" + accessoryId +
                ", accessoryType=" + accessoryType +
                ", accessoryName='" + accessoryName + '\'' +
                ", price=" + price +
                ", icon=" + svgIcon +
                '}';
    }
}
