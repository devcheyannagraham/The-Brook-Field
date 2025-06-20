package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import jakarta.persistence.*;

@Entity
public class PublicationItem extends Item {
    @Enumerated(EnumType.STRING)
    private PublicationFormat format;
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public PublicationFormat getFormat() {
        return format;
    }

    public void setFormat(PublicationFormat format) {
        this.format = format;
    }



}
