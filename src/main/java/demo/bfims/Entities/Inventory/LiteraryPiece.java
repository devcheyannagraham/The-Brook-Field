package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.ItemType;
import jakarta.persistence.*;

@Entity
public class LiteraryPiece extends PublicationItem {
    @Enumerated(EnumType.STRING)
    LiteraryType type;

    public LiteraryPiece() {
        this.setItemType(ItemType.LITERARY_PIECE);
    }

    public LiteraryType getType() {
        return type;
    }
    public void setType(LiteraryType type) {
        this.type = type;
    }


}
