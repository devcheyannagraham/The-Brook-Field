package demo.bfims.Entities;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends Publication{
    private Date date_published;
    private String edition;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();

    public Date getDate_published() {
        return date_published;
    }

    public void setDate_published(Date date_published) {
        this.date_published = date_published;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public Long getId() {
        return this.publicationId;
    }

    public void setId(Long id) {
        this.publicationId = id;
    }

}
