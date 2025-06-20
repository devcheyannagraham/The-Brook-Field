package demo.bfims.Entities.Inventory;

import demo.bfims.Entities.Orders.Item;
import demo.bfims.Enums.Genre;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public abstract class Publication extends Item {
    private String title;

    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String isbn;
    private Integer quantity = 0;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PublicationItem> copies = new ArrayList<>();

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

    public void addCopy(PublicationItem copy) {
        copy.setPublication(this);
        copies.add(copy);
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        this.quantity++;
    }

}
