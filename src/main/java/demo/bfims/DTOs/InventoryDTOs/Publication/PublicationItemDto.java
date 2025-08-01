package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;

import java.util.Date;

public class PublicationItemDto extends ItemDto {
    private PublicationItemFormat format;
    private PublicationItemStatus status;
    private PublicationDto publication;
    private PublicationItemType publicationItemType;
    private Double purchasePrice;
    private Double rentalRate;
    private String edition;

    //Journal fields for incoming dtos
    private Date issueDate;
    private int issueNumber;
    private String issueName;
    private String volume;

    //LiteraryPiece fields for incoming dtos
    LiteraryType type;

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    // only for making mulitple items
    private Integer quantity = 0;

    public PublicationItemDto() {
    }

    public PublicationItemFormat getFormat() {
        return format;
    }

    public LiteraryType getType() {
        return type;
    }

    public void setType(LiteraryType type) {
        this.type = type;
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
                ", issueDate=" + issueDate +
                ", issueNumber=" + issueNumber +
                ", issueName='" + issueName + '\'' +
                ", volume='" + volume + '\'' +
                ", type=" + type +
                ", quantity=" + quantity +
                "} " + super.toString();
    }

}
