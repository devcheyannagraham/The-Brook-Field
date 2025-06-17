package demo.bfims.Entities;

import demo.bfims.Enums.Genre;
import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long publicationId;
    protected String title;

    @Enumerated(EnumType.STRING)
    protected Genre genre;
    protected String isbn;
    protected Integer quantity = 0;

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long id) {
        this.publicationId = id;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Publication that)) return false;
        return Objects.equals(publicationId, that.publicationId) && Objects.equals(title, that.title) && genre == that.genre && Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicationId, title, genre, isbn);
    }
}
