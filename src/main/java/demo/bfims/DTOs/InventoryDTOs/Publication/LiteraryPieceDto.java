package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Enums.LiteraryType;

public class LiteraryPieceDto extends PublicationItemDto {
    LiteraryType type;

    public LiteraryType getType() {
        return type;
    }
    public void setType(LiteraryType type) {
        this.type = type;
    }


}
