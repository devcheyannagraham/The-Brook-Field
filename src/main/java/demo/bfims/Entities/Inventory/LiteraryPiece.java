package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.ItemType;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
public class LiteraryPiece extends PublicationItem {
    @Enumerated(EnumType.STRING)
    LiteraryType type;

    public LiteraryPiece() {
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
    }

    public LiteraryType getType() {
        return type;
    }
    public void setType(LiteraryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LiteraryPiece{" +
                "type=" + type +
                ", publication=" + publication +
                "} " + super.toString();
    }
}
