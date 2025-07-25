package demo.bfims.DTOs.ReportDTOs;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;

import java.util.List;

public class ItemGroup {
    private List<PublicationDto> publications;
    private List<Accessory> accessories;


    public List<PublicationDto> getPublications() {
        return publications;
    }

    public List<Accessory> getAccessories() {
        return accessories;
    }

    public void setAccessories(List<Accessory> accessories) {
        this.accessories = accessories;
    }

    @Override
    public String toString() {
        return "ItemGroup{" +
                "publications=" + publications +
                ", accessories=" + accessories +
                '}';
    }

    public void setPublications(List<PublicationDto> publications) {
        this.publications = publications;
    }

}
