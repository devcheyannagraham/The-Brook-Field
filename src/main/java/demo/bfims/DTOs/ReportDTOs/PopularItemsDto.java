package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.DTOs.InventoryDTOs.PublicationDto;

import java.util.ArrayList;
import java.util.List;

public class PopularItemsDto {
    List<PopularItem<PublicationDto>> popularPublicationsDto;

    public PopularItemsDto() {
        this.popularPublicationsDto = new ArrayList<PopularItem<PublicationDto>>();
    }

    public List<PopularItem<PublicationDto>> getPopularPublicationsDto() {
        return popularPublicationsDto;
    }

    public void setPopularPublicationsDto(List<PopularItem<PublicationDto>> popularPublicationsDto) {
        this.popularPublicationsDto = popularPublicationsDto;
    }

    public void addPopularPublicationsDto(PopularItem<PublicationDto> popularItemDto) {
        this.popularPublicationsDto.add(popularItemDto);
    }

    @Override
    public String toString() {
        return "PopularItemsDto{" +
                "popularPublicationsDto=" + popularPublicationsDto +
                '}';
    }
}



