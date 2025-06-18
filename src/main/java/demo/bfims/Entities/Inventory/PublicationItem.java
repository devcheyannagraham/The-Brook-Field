package demo.bfims.Entities.Inventory;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Publication publication;

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

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
