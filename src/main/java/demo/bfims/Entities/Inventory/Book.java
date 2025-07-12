package demo.bfims.Entities.Inventory;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends PublicationItem {
    public Book(){
        this.setPublicationItemType(PublicationItemType.BOOK);
    }

    @Override
    public String toString() {
        return "Book{" +
                "publication=" + publication +
                "} " + super.toString();
    }
}

