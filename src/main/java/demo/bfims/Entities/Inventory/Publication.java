package demo.bfims.Entities.Inventory;

import demo.bfims.Enums.Genre;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Publication{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publicationId;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Temporal(TemporalType.DATE)
    private LocalDate date_published;
    @ManyToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Author> authors = new ArrayList<>();private int publicationQuantity;
    private String title;
    private String isbn;

    public LocalDate getDate_published() {
        return date_published;
    }

    public void setDate_published(LocalDate date_published) {
        this.date_published = date_published;
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

    public void increaseQuantity(){
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

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author){
        this.authors.add(author);
    }



}
