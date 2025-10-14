package demo.bfims.Entities.Inventory.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Config.SVGIcon;
import demo.bfims.Config.SVGIconFactory;
import demo.bfims.DTOs.InventoryDTOs.Publication.*;
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
    private PublicationItemStatus publicationItemStatus;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "publication_id")
    Publication publication;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    @Enumerated(EnumType.STRING)
    private PublicationItemType publicationItemType;
    @Embedded
    private SVGIcon svgIcon;


    public PublicationItem(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus publicationItemStatus, Publication publication) {
        this.setItemType(ItemType.PUBLICATION_ITEM);
        this.format = format;
        this.publicationItemStatus = publicationItemStatus;
        this.publication = publication;
        this.purchasePrice = purchasePrice;
        this.rentalRate = rentalRate;
        this.edition = edition;
    }

    //    Construct PubItem from PubItemDto
    public PublicationItem(ItemDto itemDto) {
        super(itemDto);
        if (itemDto instanceof PublicationItemDto publicationItemDto) {
            this.svgIcon = publicationItemDto.getSvgIcon();
            this.format = publicationItemDto.getFormat();
            this.publicationItemStatus = publicationItemDto.getPublicationItemStatus();
            this.purchasePrice = publicationItemDto.getPurchasePrice();
            this.rentalRate = publicationItemDto.getRentalRate();
            this.edition = publicationItemDto.getEdition();
            this.publication = new Publication(publicationItemDto.getPublication());
            this.setPublicationItemType(publicationItemDto.getPublicationItemType());
        }
    }

    //PubItemDto -> Book | LP | Journal
    public static PublicationItem mapToPublicationItemSubclass(PublicationItemDto publicationItemDto) {
        PublicationItemType type = publicationItemDto.getPublicationItemType();
        if (type.equals(PublicationItemType.BOOK)) return new Book(publicationItemDto);
        if (type.equals(PublicationItemType.JOURNAL)) return new Journal(publicationItemDto);
        if (type.equals(PublicationItemType.LITERARY_PIECE)) return new LiteraryPiece(publicationItemDto);
        return null;
    }

    public PublicationItem() {
        this.setItemType(ItemType.PUBLICATION_ITEM);
    }

    public PublicationItemStatus getPublicationItemStatus() {
        return publicationItemStatus;
    }

    public void setPublicationItemStatus(PublicationItemStatus status) {
        this.publicationItemStatus = status;
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
        // update svg icon when type is added or changed
        if (this.publicationItemType == null || !(this.publicationItemType.equals(publicationItemType))) {
            if (!publicationItemType.equals(PublicationItemType.LITERARY_PIECE)) {
                this.svgIcon = SVGIconFactory.CreatePublicationItemIcon(publicationItemType);
            }
        }
        this.publicationItemType = publicationItemType;
    }

    public SVGIcon getSvgIcon() {
        return svgIcon;
    }

    public void setSvgIcon(SVGIcon svgIcon) {
        this.svgIcon = svgIcon;
    }

    @Override
    public String toString() {
        return "PublicationItem{" +
                "format=" + format +
                ", publicationItemStatus=" + publicationItemStatus +
                ", publication=" + publication +
                ", purchasePrice=" + purchasePrice +
                ", rentalRate=" + rentalRate +
                ", edition='" + edition + '\'' +
                ", publicationItemType=" + publicationItemType +
                ", svgIcon=" + svgIcon +
                "} " + super.toString();
    }
}
