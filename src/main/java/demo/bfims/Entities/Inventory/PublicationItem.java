package demo.bfims.Entities.Inventory;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationItemType;
import demo.bfims.Enums.PublicationStatus;
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
public abstract class PublicationItem extends Item {
    @Enumerated(EnumType.STRING)
    private PublicationFormat format;
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},  fetch = FetchType.LAZY)
    @JoinColumn(name = "publication_id")
    Publication publication;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    @Enumerated(EnumType.STRING)
    private PublicationItemType publicationItemType;


    public PublicationItem() {
        this.setItemType(ItemType.PUBLICATION);
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
