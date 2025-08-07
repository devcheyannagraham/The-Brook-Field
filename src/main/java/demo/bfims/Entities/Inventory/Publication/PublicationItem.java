package demo.bfims.Entities.Inventory.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import demo.bfims.Interfaces.Purchaseable;
import demo.bfims.Interfaces.Rentable;
import jakarta.persistence.*;

@Entity
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "publicationItemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "BOOK"),
        @JsonSubTypes.Type(value = LiteraryPiece.class, name = "LITERARY_PIECE"),
        @JsonSubTypes.Type(value = Journal.class, name = "JOURNAL")
})
public abstract class PublicationItem extends Item implements Rentable, Purchaseable {
    @Enumerated(EnumType.STRING)
    private PublicationItemFormat format;
    @Enumerated(EnumType.STRING)
    private PublicationItemStatus status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "publication_id")
    Publication publication;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    @Enumerated(EnumType.STRING)
    private PublicationItemType publicationItemType;

    public PublicationItem( String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate ,  PublicationItemStatus status,Publication publication) {
        this.format = format;
        this.status = status;
        this.publication = publication;
        this.purchasePrice = purchasePrice;
        this.rentalRate = rentalRate;
        this.edition = edition;
        this.setItemType(ItemType.PUBLICATION_ITEM);
    }

    public PublicationItem(PublicationItemDto publicationItemDto) {
        super(publicationItemDto);
        this.format = publicationItemDto.getFormat();
        this.status = publicationItemDto.getStatus();
        this.purchasePrice = publicationItemDto.getPurchasePrice();
        this.rentalRate = publicationItemDto.getRentalRate();
        this.edition = publicationItemDto.getEdition();
        this.publicationItemType = publicationItemDto.getPublicationItemType();
        this.publication = new Publication(publicationItemDto.getPublication());
    }

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
