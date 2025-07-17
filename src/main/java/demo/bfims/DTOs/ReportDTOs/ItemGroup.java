package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.DTOs.InventoryDTOs.PublicationDto;
import demo.bfims.Entities.Inventory.Publication;

import java.util.ArrayList;
import java.util.List;

public class ItemGroup {
    private List<PublicationDto> publications;
//    private List<Stationary> stationary;
//    private List<Accessory> accessory;


    public List<PublicationDto> getPublications() {
        return publications;
    }

    public void setPublications(List<PublicationDto> publications) {
        this.publications = publications;
    }

    @Override
    public String toString() {
        return "ItemGroup{" +
                "publications=" + publications +
                '}';
    }
}
