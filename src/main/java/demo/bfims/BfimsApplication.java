package demo.bfims;

import demo.bfims.Entities.Author;
import demo.bfims.Entities.Book;
import demo.bfims.Entities.PublicationItem;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import demo.bfims.Repo.BookRepo;
import demo.bfims.Repo.PublicationItemRepo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.Date;

@SpringBootApplication
public class BfimsApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BfimsApplication.class, args);
        Book book = new Book();
        book.setTitle("Book Title goes here");
        book.setEdition("Book Edition goes here");
        book.setDate_published(new Date());
        book.increaseQuantity();
        book.setGenre(Genre.COMEDY);

        Author author = new Author();
        author.setFirstName("Author First Name goes here");
        author.setLastName("Author Last Name goes here");

        book.addAuthor(author);

        BookRepo bookRepo = (BookRepo) context.getBean("bookRepo");
        bookRepo.save(book);

        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setFormat(PublicationFormat.HARDCOPY);
        publicationItem.setStatus(PublicationStatus.AVAILABLE);
        publicationItem.setPublicationId(book.getPublicationId());

        PublicationItemRepo publicationItemRepo = (PublicationItemRepo) context.getBean("publicationItemRepo");
        publicationItemRepo.save(publicationItem);
    }

}
