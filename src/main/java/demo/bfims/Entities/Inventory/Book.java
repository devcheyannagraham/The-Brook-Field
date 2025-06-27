package demo.bfims.Entities.Inventory;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends PublicationItem {
}

