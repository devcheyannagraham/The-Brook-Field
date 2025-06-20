package demo.bfims.Entities.Inventory;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Book extends Publication{
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private PublicationItem publicationItem;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Author> authors = new ArrayList<>();
    private String edition;

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

    public PublicationItem getPublicationItem() {
        return publicationItem;
    }

    public void setPublicationItem(PublicationItem publicationItem) {
        this.publicationItem = publicationItem;
    }



}
