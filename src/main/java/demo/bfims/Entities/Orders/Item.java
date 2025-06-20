package demo.bfims.Entities.Orders;

import demo.bfims.Enums.ItemType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    // Common interface for publications and bookmarks and mugs etc. lets see...
    //Individual item, not class of item

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator="item_generator")
    @TableGenerator(name="item_generator")
    private Long itemId;

    private ItemType itemType;

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


}
