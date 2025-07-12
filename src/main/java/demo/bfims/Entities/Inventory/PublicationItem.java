package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
public abstract class PublicationItem extends Item {
    @Enumerated(EnumType.STRING)
    private PublicationItemFormat format;
    @Enumerated(EnumType.STRING)
    private PublicationItemStatus status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
    Publication publication;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    @Enumerated(EnumType.STRING)
    private PublicationItemType publicationItemType;


    public PublicationItem() {
        this.setItemType(ItemType.PUBLICATION_ITEM);
    }

    public PublicationItemStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationItemStatus status) {
        this.status = status;
    }

    public PublicationItemFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationItemFormat format) {
        this.format = format;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
        // assuming a new publication item is being added
        if(this.getPublication() != null)
            this.publication.increaseQuantity();
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

    public PublicationItemType getPublicationItemType() {
        return publicationItemType;
    }

    public void setPublicationItemType(PublicationItemType publicationItemType) {
        this.publicationItemType = publicationItemType;
    }

    @Override
    public String toString() {
        return "PublicationItem{" +
                "format=" + format +
                ", status=" + status +
                ", publication=" + publication +
                ", purchasePrice=" + purchasePrice +
                ", rentalRate=" + rentalRate +
                ", edition='" + edition + '\'' +
                ", publicationItemType=" + publicationItemType +
                "} " + super.toString();
    }
}
