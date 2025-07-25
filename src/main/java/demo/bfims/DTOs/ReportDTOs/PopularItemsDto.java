package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;

import java.util.ArrayList;
import java.util.List;

public class PopularItemsDto {
    List<PopularItem<PublicationDto>> popularPublicationsDto;
    List<PopularItem<AccessoryDto>> popularAccessoriesDto;

    public List<PopularItem<AccessoryDto>> getPopularAccessoriesDto() {
        return popularAccessoriesDto;
    }

    public void setPopularAccessoriesDto(List<PopularItem<AccessoryDto>> popularAccessoriesDto) {
        this.popularAccessoriesDto = popularAccessoriesDto;
    }

    public PopularItemsDto() {

        this.popularPublicationsDto = new ArrayList<>();
        this.popularAccessoriesDto = new ArrayList<>();
    }

    public List<PopularItem<PublicationDto>> getPopularPublicationsDto() {
        return popularPublicationsDto;
    }

    public void setPopularPublicationsDto(List<PopularItem<PublicationDto>> popularPublicationsDto) {
        this.popularPublicationsDto = popularPublicationsDto;
    }

    @Override
    public String toString() {
        return "PopularItemsDto{" +
                "popularPublicationsDto=" + popularPublicationsDto +
                ", popularAccessoriesDto=" + popularAccessoriesDto +
                '}';
    }
}



