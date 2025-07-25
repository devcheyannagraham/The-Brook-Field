package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;

import java.util.List;

public class ItemGroup {
    private List<PublicationDto> publications;
    private List<Accessory> accessory;


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
