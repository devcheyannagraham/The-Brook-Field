package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Enums.ItemType;

public class ShopPopularItemDto {
    Long id;
    String title;
    ItemType itemType;

    public ShopPopularItemDto(PopularItemDto popularItemDto) {
        this.id = popularItemDto.getId();
        this.title = popularItemDto.getTitle();
        this.itemType = popularItemDto.getItemType();
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

    @Override
    public String toString() {
        return "ShopPopularItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", itemType=" + itemType +
                '}';
    }
}
