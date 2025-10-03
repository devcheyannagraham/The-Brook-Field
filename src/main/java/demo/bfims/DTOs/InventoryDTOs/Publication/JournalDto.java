package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Entities.Inventory.Publication.Journal;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.PublicationItemType;

import java.time.LocalDate;

public class JournalDto extends PublicationItemDto {
    private LocalDate issueDate;
    private String issueNumber;
    private String issueName;
    private String volume;

    public JournalDto() {
        this.setPublicationItemType(PublicationItemType.JOURNAL);
    }

    public JournalDto(PublicationItem publicationItem) {
        super(publicationItem);
        if (publicationItem instanceof Journal journal) {
            this.issueDate = journal.getIssueDate();
            this.issueNumber = journal.getIssueNumber();
            this.issueName = journal.getIssueName();
            this.volume = journal.getVolume();
        }
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getIssueName() {
        return issueName;
    }

    public void setIssueName(String issueName) {
        this.issueName = issueName;
    }

    public String getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(String issueNumber) {
        this.issueNumber = issueNumber;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "JournalDto{" +
                "issueDate=" + issueDate +
                ", issueNumber=" + issueNumber +
                ", issueName='" + issueName + '\'' +
                ", volume='" + volume + '\'' +
                "} " + super.toString();
    }
}
