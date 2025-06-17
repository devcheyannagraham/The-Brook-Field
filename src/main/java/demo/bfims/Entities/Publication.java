package demo.bfims.Entities;

import demo.bfims.Enums.Genre;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "publication_id_generator")
    @TableGenerator(name = "publication_id_generator")
    private Long publicationId;
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String isbn;
    private Integer quantity = 0;

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
