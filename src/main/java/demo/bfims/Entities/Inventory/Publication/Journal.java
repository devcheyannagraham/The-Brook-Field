package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
public class Journal extends PublicationItem {
    @Temporal(TemporalType.DATE)
    private LocalDate issueDate;
    private int issueNumber;
    private String issueName;
    private String volume;

    public Journal() {
        this.setPublicationItemType(PublicationItemType.JOURNAL);
    }

    public Journal(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus status, Publication publication, LocalDate issueDate, String issueName, int issueNumber, String volume) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.issueDate = issueDate;
        this.issueNumber = issueNumber;
        this.issueName = issueName;
        this.volume = volume;
        this.setPublicationItemType(PublicationItemType.JOURNAL);
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
