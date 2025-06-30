package demo.bfims.DTOs.InventoryDTOs;

import demo.bfims.Entities.Inventory.PublicationItem;

import java.util.Date;

public class JournalDto extends PublicationItemDto {
    private Date issueDate;
    private int issueNumber;
    private String issueName;
    private String volume;

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

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }
}
