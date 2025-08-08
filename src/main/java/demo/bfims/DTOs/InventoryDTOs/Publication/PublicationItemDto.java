package demo.bfims.DTOs.InventoryDTOs.Publication;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Enums.*;

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
    private PublicationItemStatus status;
    private PublicationDto publication;
    private PublicationItemType publicationItemType;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;

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
            this.status = publicationItem.getStatus();
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

    public PublicationItemFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationItemFormat format) {
        this.format = format;
    }

    public PublicationItemStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationItemStatus status) {
        this.status = status;
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
                ", status=" + status +
                ", publication=" + publication +
                ", publicationItemType=" + publicationItemType +
                ", purchasePrice=" + purchasePrice +
                ", rentalRate=" + rentalRate +
                ", edition='" + edition + '\'' +
                ", quantity=" + quantity +
                "} " + super.toString();
    }

}
