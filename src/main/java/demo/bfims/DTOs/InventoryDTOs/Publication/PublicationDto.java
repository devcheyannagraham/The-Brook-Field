package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Enums.Genre;

import java.time.LocalDate;

public class PublicationDto {
    private Long publicationId;
    private Genre genre;
    private LocalDate datePublished;
    private AuthorDto author;
    private String title;
    private String isbn;

    public PublicationDto(){}

    public PublicationDto(Publication publication) {
        this.publicationId = publication.getPublicationId();
        this.genre = publication.getGenre();
        this.datePublished = publication.getDatePublished();
        this.title = publication.getTitle();
        this.isbn = publication.getIsbn();
        this.author = new AuthorDto(publication.getAuthor());
    }

    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(LocalDate datePublished) {
        this.datePublished = datePublished;
    }

    public AuthorDto getAuthor() {
        return author;
    }

    public void setAuthor(AuthorDto author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "PublicationDto{" +
                "publicationId=" + publicationId +
                ", genre=" + genre +
                ", datePublished=" + datePublished +
                ", author=" + author +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
