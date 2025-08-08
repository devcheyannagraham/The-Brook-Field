package demo.bfims.DTOs.InventoryDTOs.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.Entities.Inventory.Accessory.AccessoryItem;
import demo.bfims.Entities.Inventory.Publication.Item;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.ItemType;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PublicationItemDto.class, name = "PUBLICATION_ITEM"),
        @JsonSubTypes.Type(value = AccessoryItemDto.class, name = "ACCESSORY_ITEM"),
})
public class ItemDto {
    private Long itemId;
    private ItemType itemType;

    public ItemDto() {
    }

    //Construct ItemDto from Item
    public ItemDto(Item item){
        this.itemId = item.getItemId();
        this.itemType = item.getItemType();
    }

//    Item => PublicationItemDto | AccessorytItemDto
    public static ItemDto mapToItemDtoSubclass(Item item){
        if(item instanceof PublicationItem){
            return PublicationItemDto.mapToPublicationItemDtoSubclass((PublicationItem) item);
        }
        else if(item instanceof AccessoryItem){
            return new AccessoryItemDto(item);
        }
        return null;
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
        return "ItemDto{" +
                "itemId=" + itemId +
                ", itemType=" + itemType +
                '}';
    }
}
