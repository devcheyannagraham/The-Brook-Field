package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import jakarta.persistence.*;

@Entity
public abstract class PublicationItem extends Item {
    @Enumerated(EnumType.STRING)
    private PublicationFormat format;
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;
    @ManyToOne(fetch = FetchType.LAZY)
    Publication publication;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;


    public PublicationItem() {
        setItemType(ItemType.PUBLICATION);
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public PublicationFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationFormat format) {
        this.format = format;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public Double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(Double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }


}
