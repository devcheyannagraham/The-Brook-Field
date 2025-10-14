package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.Config.SVGIcon;
import demo.bfims.Config.SVGIconFactory;
import demo.bfims.DTOs.InventoryDTOs.Publication.LiteraryPieceDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
public class LiteraryPiece extends PublicationItem {
    @Enumerated(EnumType.STRING)
    private LiteraryType literaryType;

    public LiteraryPiece() {
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
    }

    public LiteraryPiece(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus status, Publication publication, LiteraryType literaryType) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
        this.setLiteraryType(literaryType);
    }

    public LiteraryPiece(PublicationItemDto publicationItemDto) {
        super(publicationItemDto);
        if (publicationItemDto instanceof LiteraryPieceDto literaryPieceDto) {
            this.setLiteraryType(literaryPieceDto.getLiteraryType());
        }
    }

    public LiteraryType getLiteraryType() {
        return literaryType;
    }

    public void setLiteraryType(LiteraryType type) {
//        no literary type set or its modified
        if (this.literaryType == null || !(this.literaryType.equals(type))) {
            this.setSvgIcon(SVGIconFactory.CreateLiteraryPieceIcon(type));
        }
        this.literaryType = type;
    }

    @Override
    public String toString() {
        return "LiteraryPiece{" +
                "type=" + literaryType +
                ", publication=" + publication +
                "} " + super.toString();
    }
}
