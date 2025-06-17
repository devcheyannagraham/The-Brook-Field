package demo.bfims;

import demo.bfims.Entities.Author;
import demo.bfims.Entities.Book;
import demo.bfims.Entities.LiteraryPiece;
import demo.bfims.Entities.PublicationItem;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import demo.bfims.Repo.BookRepo;
import demo.bfims.Repo.LiteraryPieceRepo;
import demo.bfims.Repo.PublicationItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.Date;

@SpringBootApplication
public class BfimsApplication {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private PublicationItemRepo publicationItemRepo;

    @Autowired
    private LiteraryPieceRepo literaryPieceRepo;


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BfimsApplication.class, args);
        BfimsApplication app = (BfimsApplication) context.getBean("bfimsApplication");
        app.addBook();
        app.addLiteraryPiece();

    }

    public void addBook(){
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
        bookRepo.save(book);

        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setFormat(PublicationFormat.HARDCOPY);
        publicationItem.setStatus(PublicationStatus.AVAILABLE);
        publicationItem.setPublicationId(book.getPublicationId());

        publicationItemRepo.save(publicationItem);

    }

    public void addLiteraryPiece(){
        LiteraryPiece literaryPiece = new LiteraryPiece();
        literaryPiece.setTitle("LiteraryPieceRepo Piece goes here");
        literaryPiece.setEdition("LiteraryPieceRepo Piece goes here");
        literaryPiece.setDate_published(new Date());
        literaryPiece.increaseQuantity();
        literaryPiece.setGenre(Genre.COMEDY);
        literaryPiece.setType(LiteraryType.POEM);

        Author author = new Author();
        author.setFirstName("Author First Name goes here");
        author.setLastName("Author Last Name goes here");
        literaryPiece.addAuthor(author);

        literaryPieceRepo.save(literaryPiece);


        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setFormat(PublicationFormat.AUDIOBOOK);
        publicationItem.setStatus(PublicationStatus.RENTED);
        publicationItem.setPublicationId(literaryPiece.getPublicationId());
        publicationItemRepo.save(publicationItem);

    }

}
