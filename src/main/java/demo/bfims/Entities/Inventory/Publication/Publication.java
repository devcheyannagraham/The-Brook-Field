package demo.bfims.Entities.Inventory.Publication;

import demo.bfims.Enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationId;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Temporal(TemporalType.DATE)
    private LocalDate datePublished;
    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private Author author;
    private String title;
    private String isbn;

    public Publication() {
    }

    public Publication( LocalDate datePublished, Genre genre, String isbn,  String title,Author author) {
        this.genre = genre;
        this.datePublished = datePublished;
        this.author = author;
        this.title = title;
        this.isbn = isbn;
    }

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

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Publication{" +
                "publicationId=" + publicationId +
                ", genre=" + genre +
                ", date_published=" + datePublished +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
