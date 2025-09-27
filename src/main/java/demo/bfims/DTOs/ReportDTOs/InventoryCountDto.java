package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Enums.ItemType;

import java.util.Objects;

public class InventoryCountDto {
    Long id;
    ItemType itemType;
    String title;
    Integer count;

    public InventoryCountDto(Publication pub, Integer count) {
        this.id = pub.getPublicationId();
        this.itemType = ItemType.PUBLICATION_ITEM;
        this.title = pub.getTitle();
        this.count = count;
    }

    public InventoryCountDto(Accessory acc, Integer count){
        this.id = acc.getAccessoryId();
        this.itemType = ItemType.ACCESSORY_ITEM;
        this.title = acc.getAccessoryName();
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public String toString() {
        return "InventoryCountDto{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof InventoryCountDto that)) return false;
        return Objects.equals(id, that.id) && itemType == that.itemType && Objects.equals(title, that.title) && Objects.equals(count, that.count);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, itemType, title, count);
    }
}




