package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Entities.Inventory.Publication.LiteraryPiece;
import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationItemType;

public class LiteraryPieceDto extends PublicationItemDto {
    private LiteraryType literaryType;

    public LiteraryPieceDto() {
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
    }
    public LiteraryPieceDto(LiteraryPiece literaryPiece) {
        super(literaryPiece);
        this.literaryType = literaryPiece.getLiteraryType();
    }


    public LiteraryType getLiteraryType() {
        return literaryType;
    }
    public void setLiteraryType(LiteraryType literaryType) {
        this.literaryType = literaryType;
    }





}
