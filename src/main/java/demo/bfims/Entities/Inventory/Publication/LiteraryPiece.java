package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Enums.PublicationItemType;
import jakarta.persistence.*;

@Entity
public class LiteraryPiece extends PublicationItem {
    @Enumerated(EnumType.STRING)
    LiteraryType literaryType;

    public LiteraryPiece() {
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
    }

    public LiteraryPiece(String edition, PublicationItemFormat format, Double purchasePrice, Double rentalRate, PublicationItemStatus status, Publication publication, LiteraryType literaryType) {
        super(edition, format, purchasePrice, rentalRate, status, publication);
        this.setPublicationItemType(PublicationItemType.LITERARY_PIECE);
        this.literaryType = literaryType;
    }

    public LiteraryType getLiteraryType() {
        return literaryType;
    }
    public void setLiteraryType(LiteraryType type) {
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
