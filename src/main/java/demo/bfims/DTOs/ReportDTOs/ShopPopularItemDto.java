package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Enums.ItemType;

public class ShopPopularItemDto {
    Long id;
    String title;
    ItemType itemType;
    SVGIcon svgIcon;

    public ShopPopularItemDto(PopularItemDto popularItemDto) {
        this.id = popularItemDto.getId();
        this.title = popularItemDto.getTitle();
        this.itemType = popularItemDto.getItemType();
        this.svgIcon = popularItemDto.getSvgIcon();

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
        return "ShopPopularItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", itemType=" + itemType +
                ", svgIcon=" + svgIcon +
                '}';
    }
}
