package demo.bfims.Entities.Inventory.Accessory;


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

    public Long getAccessoryId() {
        return accessoryId;
    }

    public Accessory(String accessoryName,AccessoryType accessoryType, double price) {
        this.accessoryType = accessoryType;
        this.accessoryName = accessoryName;
        this.price = price;
    }

    public Accessory() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Accessory{" +
                "accessoryId=" + accessoryId +
                ", accessoryType=" + accessoryType +
                ", accessoryName='" + accessoryName + '\'' +
                ", price=" + price +
                '}';
    }
}
