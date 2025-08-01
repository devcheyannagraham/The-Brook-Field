package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.Enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationId;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Temporal(TemporalType.DATE)
    private LocalDate datePublished;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Author author;
    private int publicationQuantity;
    private String title;
    private String isbn;

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate date_published) {
        this.datePublished = date_published;
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public int getPublicationQuantity() {
        return publicationQuantity;
    }

    public void setPublicationQuantity(int quantity) {
        this.publicationQuantity = quantity;
    }

    public void increaseQuantity() {
        this.publicationQuantity++;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author authors) {
        this.author = authors;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "publicationId=" + publicationId +
                ", genre=" + genre +
                ", date_published=" + datePublished +
                ", authors=" + author +
                ", publicationQuantity=" + publicationQuantity +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
