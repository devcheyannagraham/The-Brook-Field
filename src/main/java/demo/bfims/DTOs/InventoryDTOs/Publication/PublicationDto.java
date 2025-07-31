package demo.bfims.DTOs.InventoryDTOs.Publication;

import demo.bfims.Enums.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PublicationDto {
    private Long publicationId;
    private Genre genre;
    private LocalDate datePublished;
    private List<AuthorDto> authors = new ArrayList<>();
    private int publicationQuantity;
    private String title;
    private String isbn;

    public PublicationDto(){}

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

    public List<AuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorDto> authors) {
        this.authors = authors;
    }

    public int getPublicationQuantity() {
        return publicationQuantity;
    }

    public void setPublicationQuantity(int publicationQuantity) {
        this.publicationQuantity = publicationQuantity;
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
                ", authors=" + authors +
                ", publicationQuantity=" + publicationQuantity +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}
