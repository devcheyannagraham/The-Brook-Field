package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Journal extends PublicationItem {
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    private int issueNumber;
    private String issueName;
    private String volume;

    public Journal() {
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

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
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
