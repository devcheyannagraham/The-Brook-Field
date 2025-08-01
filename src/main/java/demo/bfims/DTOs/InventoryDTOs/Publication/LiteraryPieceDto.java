package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Enums.LiteraryType;

public class LiteraryPieceDto extends PublicationItemDto {
    LiteraryType type;

    public LiteraryType getLiteraryType() {
        return type;
    }
    public void setLiteraryType(LiteraryType literaryType) {
        this.type = literaryType;
    }


}
