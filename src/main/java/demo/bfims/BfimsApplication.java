package demo.bfims;

import demo.bfims.Entities.Inventory.*;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.LiteraryType;
import demo.bfims.Enums.PublicationFormat;
import demo.bfims.Enums.PublicationStatus;
import demo.bfims.Repo.PublicationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import java.util.Date;

@SpringBootApplication
public class BfimsApplication {
    @Autowired
    private PublicationRepo publicationRepo;

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(BfimsApplication.class, args);
        BfimsApplication app = (BfimsApplication) context.getBean("bfimsApplication");
        app.addBook();
        app.addLiteraryPiece();
        app.addJournal();

    }

    private void addJournal() {
        Journal journal = new Journal();
        journal.setTitle("Journal 1");
        journal.setIssueDate(new Date());
        journal.setIssueName("I Have ISSUES");
        journal.setIssueNumber(31);
        journal.setVolume("II");
        journal.setGenre(Genre.SCIFI);
        journal.increaseQuantity();

        Author author = new Author();
        author.setFirstName("First Author");
        author.setLastName("Last Author");
        journal.addPublisher(author);

        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setStatus(PublicationStatus.PURCHASED);
        publicationItem.setFormat(PublicationFormat.EBOOK);
        journal.addCopy(publicationItem);
        publicationRepo.save(journal);



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

        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setFormat(PublicationFormat.HARDCOPY);
        publicationItem.setStatus(PublicationStatus.AVAILABLE);

        book.addAuthor(author);
        book.addCopy(publicationItem);
        publicationRepo.save(book);
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

        PublicationItem publicationItem = new PublicationItem();
        publicationItem.setFormat(PublicationFormat.AUDIOBOOK);
        publicationItem.setStatus(PublicationStatus.RENTED);
        literaryPiece.addCopy(publicationItem);

        publicationRepo.save(literaryPiece);
    }

}
