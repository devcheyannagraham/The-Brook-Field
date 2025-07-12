package demo.bfims.Entities.Inventory;
import demo.bfims.Enums.ItemType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends PublicationItem {
    public Book(){
        this.setItemType(ItemType.BOOK);
    }

    @Override
    public String toString() {
        return "Book{" +
                "publication=" + publication +
                "} " + super.toString();
    }
}

