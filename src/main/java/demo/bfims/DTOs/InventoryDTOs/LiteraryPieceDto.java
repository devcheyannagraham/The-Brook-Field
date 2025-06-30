package demo.bfims.DTOs.InventoryDTOs;

import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationItemType;

public class LiteraryPieceDto extends PublicationItemDto {
    LiteraryType type;

    public LiteraryType getType() {
        return type;
    }
    public void setType(LiteraryType type) {
        this.type = type;
    }


}
