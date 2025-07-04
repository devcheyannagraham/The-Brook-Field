package demo.bfims.Entities.Order;


import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Enums.ItemOrderType;
import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @Enumerated(EnumType.STRING)
    private ItemOrderType itemOrderType;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.PERSIST})
    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }


    public ItemOrderType getItemOrderType() {
        return itemOrderType;
    }

    public void setItemOrderType(ItemOrderType itemOrderType) {
        this.itemOrderType = itemOrderType;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", itemOrderType=" + itemOrderType +
                ", item=" + item +
                '}';
    }
}
