package demo.bfims.DTOs.InventoryDTOs.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Config.SVGIcon;
import demo.bfims.Config.SVGIconFactory;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Enums.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.PrePersist;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "publicationItemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BookDto.class, name = "BOOK"),
        @JsonSubTypes.Type(value = LiteraryPieceDto.class, name = "LITERARY_PIECE"),
        @JsonSubTypes.Type(value = JournalDto.class, name = "JOURNAL")
})
public class PublicationItemDto extends ItemDto {
    private PublicationItemFormat format;
    private PublicationItemStatus publicationItemStatus;
    private PublicationDto publication;
    private PublicationItemType publicationItemType;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    @Embedded
    private SVGIcon svgIcon;

    // only for making mulitple items
    private Integer quantity;

    public PublicationItemDto() {
        this.setItemType(ItemType.PUBLICATION_ITEM);
    }


    //    Construct PubItemDto from PubItem
    public PublicationItemDto(Item item) {
        super(item);
        if (item instanceof PublicationItem publicationItem) {
            this.format = publicationItem.getFormat();
            this.publicationItemStatus = publicationItem.getPublicationItemStatus();
            this.purchasePrice = publicationItem.getPurchasePrice();
            this.rentalRate = publicationItem.getRentalRate();
            this.edition = publicationItem.getEdition();
            this.publicationItemType = publicationItem.getPublicationItemType();
            this.publication = new PublicationDto(publicationItem.getPublication());
        }
    }

    //PubItem -> BookDto | LPDto | JournalDto
    public static PublicationItemDto mapToPublicationItemDtoSubclass(PublicationItem publicationItem) {
        PublicationItemType type = publicationItem.getPublicationItemType();
        if (type.equals(PublicationItemType.BOOK)) return new BookDto(publicationItem);
        if (type.equals(PublicationItemType.JOURNAL)) return new JournalDto(publicationItem);
        if (type.equals(PublicationItemType.LITERARY_PIECE))
            return new LiteraryPieceDto(publicationItem);
        return null;
    }

    @PrePersist
    public void prePersist(){
        this.svgIcon = SVGIconFactory.CreatePublicationItemIcon(this.publicationItemType);
    }

    public PublicationItemFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationItemFormat format) {
        this.format = format;
    }

    public PublicationItemStatus getPublicationItemStatus() {
        return publicationItemStatus;
    }

    public void setPublicationItemStatus(PublicationItemStatus publicationItemStatus) {
        this.publicationItemStatus = publicationItemStatus;
    }

    public PublicationDto getPublication() {
        return publication;
    }

    public void setPublication(PublicationDto publication) {
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

    public SVGIcon getSvgIcon() {
        return svgIcon;
    }

    public void setSvgIcon(SVGIcon svgIcon) {
        this.svgIcon = svgIcon;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "PublicationItemDto{" +
                "format=" + format +
                ", publicationItemStatus=" + publicationItemStatus +
                ", publication=" + publication +
                ", publicationItemType=" + publicationItemType +
                ", purchasePrice=" + purchasePrice +
                ", rentalRate=" + rentalRate +
                ", edition='" + edition + '\'' +
                ", svgIcon=" + svgIcon +
                ", quantity=" + quantity +
                "} " + super.toString();
    }

}
