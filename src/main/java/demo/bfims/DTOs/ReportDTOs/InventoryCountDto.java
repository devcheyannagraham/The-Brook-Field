package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Enums.ItemType;

import java.util.Objects;

public class InventoryCountDto {
    Long id;
    ItemType itemType;
    String title;
    Integer count;
    SVGIcon svgIcon;

    public InventoryCountDto(Publication pub, Integer count) {
        this.id = pub.getPublicationId();
        this.itemType = ItemType.PUBLICATION_ITEM;
        this.title = pub.getTitle();
        this.count = count;
        this.svgIcon = pub.getSvgIcon();
    }

    public InventoryCountDto(Accessory acc, Integer count){
        this.id = acc.getAccessoryId();
        this.itemType = ItemType.ACCESSORY_ITEM;
        this.title = acc.getAccessoryName();
        this.count = count;
        this.svgIcon = acc.getSvgIcon();
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

    public SVGIcon getSvgIcon() {
        return svgIcon;
    }

    @Override
    public String toString() {
        return "InventoryCountDto{" +
                "id=" + id +
                ", itemType=" + itemType +
                ", title='" + title + '\'' +
                ", count=" + count +
                ", svgIcon=" + svgIcon +
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




