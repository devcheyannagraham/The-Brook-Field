package demo.bfims.Entities;

import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import jakarta.persistence.*;

@Entity
public class PublicationItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private PublicationFormat format;

    @Enumerated(EnumType.STRING)
    private PublicationStatus status;
    // This uses the id instead of the object since the parent class (Publication) cannot be a type
    private Long publicationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
        System.out.println("PublicationItem setPublicationId " + publicationId);
    }
}
