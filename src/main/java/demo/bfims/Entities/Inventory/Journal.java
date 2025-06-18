package demo.bfims.Entities.Inventory;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Journal extends Publication {
    private int issueNumber;
    private String issueName;
    private String volume;
    private Date issueDate;

    @ManyToMany(cascade = CascadeType.ALL)
    List<Author> publishers = new ArrayList<>();

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

    public List<Author> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Author> publishers) {
        this.publishers = publishers;
    }

    public void addPublisher(Author publisher){
        publishers.add(publisher);
    }
}
