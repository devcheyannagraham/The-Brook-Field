package demo.bfims.DTOs.InventoryDTOs;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationStatus;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "itemType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = BookDto.class, name = "BOOK")
})
public abstract class PublicationItemDto extends ItemDto {
    private PublicationFormat format;
    private PublicationStatus status;
    private PublicationDto publicationDto;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;
    private ItemType itemType;

    public PublicationItemDto() {
    }

    public PublicationFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationFormat format) {
        this.format = format;
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public PublicationDto getPublicationDto() {
        return publicationDto;
    }

    public void setPublicationDto(PublicationDto publicationDto) {
        this.publicationDto = publicationDto;
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

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }
}
