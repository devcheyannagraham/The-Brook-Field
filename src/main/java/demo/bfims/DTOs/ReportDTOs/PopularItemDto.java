package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;

public class PopularItemDto {
    Long id;
    String title;
    Integer totalUnitsSold;
    Double totalProfit;

    public PopularItemDto() {
    }

    public PopularItemDto(Accessory acc){
        this.id=acc.getAccessoryId();
        this.title=acc.getAccessoryName();
    }

    public PopularItemDto(Publication pub){
        this.id=pub.getPublicationId();
        this.title=pub.getTitle();
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

    @Override
    public String toString() {
        return "PopularItemDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", totalUnitsSold=" + totalUnitsSold +
                ", totalProfit=" + totalProfit +
                '}';
    }
}
