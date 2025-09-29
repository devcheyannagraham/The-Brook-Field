package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Enums.ItemType;

public class PopularItemDto {
    Long id;
    String title;
    Integer totalUnitsSold;
    Double totalProfit;
    ItemType itemType;
    SVGIcon svgIcon;

    public PopularItemDto() {
    }

    public PopularItemDto(Accessory acc){
        this.id=acc.getAccessoryId();
        this.title=acc.getAccessoryName();
        this.itemType = ItemType.ACCESSORY_ITEM;
        this.svgIcon = acc.getSvgIcon();
    }

    public PopularItemDto(Publication pub){
        this.id=pub.getPublicationId();
        this.title=pub.getTitle();
        this.itemType = ItemType.PUBLICATION_ITEM;
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

    public Integer getTotalUnitsSold() {
        return totalUnitsSold;
    }

    public void setTotalUnitsSold(Integer totalUnitsSold) {
        this.totalUnitsSold = totalUnitsSold;
    }

    public Double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(Double totalProfit) {
        this.totalProfit = totalProfit;
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

    public void setSvgIcon(SVGIcon svgIcon) {
        this.svgIcon = svgIcon;
    }

    @Override
    public String toString() {
        return "PopularItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", totalUnitsSold=" + totalUnitsSold +
                ", totalProfit=" + totalProfit +
                ", itemType=" + itemType +
                ", svgIcon=" + svgIcon +
                '}';
    }
}
