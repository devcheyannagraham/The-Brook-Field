package demo.bfims.Entities.Inventory.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Enums.ItemType;
import jakarta.persistence.*;

@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PublicationItem.class, name = "PUBLICATION_ITEM"),
        @JsonSubTypes.Type(value = AccessoryItem.class, name = "ACCESSORY_ITEM"),
})
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Item {
    // Common interface for publications and bookmarks and mugs etc. lets see...
    //Individual item, not class of item

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "item_generator")
    @TableGenerator(name = "item_generator")
    private Long itemId;
    @Enumerated(EnumType.STRING)
    private ItemType itemType;

    public Item() {
    }

    public Item(ItemDto  itemDto) {
        this.itemId = itemDto.getItemId();
        this.itemType = itemDto.getItemType();
    }

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

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemType=" + itemType +
                '}';
    }
}
