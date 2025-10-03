package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.DTOs.InventoryDTOs.Publication.JournalDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Journal extends PublicationItem {
    @Temporal(TemporalType.DATE)
    private LocalDate issueDate;
    private String issueNumber;
    private String issueName;
    private String volume;

    public Journal() {
        this.setPublicationItemType(PublicationItemType.JOURNAL);
    }

    public Journal(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus status, Publication publication, LocalDate issueDate, String issueName, String issueNumber, String volume) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.issueDate = issueDate;
        this.issueNumber = issueNumber;
        this.issueName = issueName;
        this.volume = volume;
        this.setPublicationItemType(PublicationItemType.JOURNAL);
    }

    public Journal(PublicationItemDto publicationItem) {
        //PubItem Fields
        super(publicationItem);
        if (publicationItem instanceof JournalDto journalDto) {
            // donwncasting
            this.issueDate = journalDto.getIssueDate();
            this.issueNumber = journalDto.getIssueNumber();
            this.issueName = journalDto.getIssueName();
            this.volume = journalDto.getVolume();
        }
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
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

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }


    @Override
    public String toString() {
        return "Journal{" +
                "issueDate=" + issueDate +
                ", issueNumber=" + issueNumber +
                ", issueName='" + issueName + '\'' +
                ", volume='" + volume + '\'' +
                ", publication=" + publication +
                "} " + super.toString();
    }
}
