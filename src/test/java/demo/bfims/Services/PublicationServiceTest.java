package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.PublicationRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PublicationServiceTest {

    static PublicationItem availablePublicationItem;
    static PublicationItem unavailablePublicationItem;
    static Publication publication;

    @Autowired
    PublicationService publicationService;

    @Autowired
    PublicationRepo publicationRepo;

    @Test
    @Order(1)
    void newPublication() {
        Publication pub = new Publication();
        pub.setAuthor(new Author("John", "Doe"));
        pub.setTitle("Book Title 1");
        pub.setGenre(Genre.FANTASY);
        publication =  publicationRepo.save(pub);
        assertNotNull(publication);
        assertNull(this.publicationService.newPublication(null));
    }

    @Test
    @Order(2)
    void newPublicationItem() {
        Book availableBook = new Book();
        availableBook.setPublication(publication);
        availableBook.setPublicationItemStatus(PublicationItemStatus.AVAILABLE);
        availablePublicationItem = new Book(publicationService.newPublicationItem(new PublicationItemDto(availableBook)));
        assertNotNull(availablePublicationItem);

        Journal unavailableJournal = new Journal();
        unavailableJournal.setPublication(publication);
        unavailableJournal.setPublicationItemStatus(PublicationItemStatus.RENTED);
        unavailablePublicationItem = new Journal(publicationService.newPublicationItem(new PublicationItemDto(unavailableJournal)));
        assertNotNull(unavailablePublicationItem);

        assertNull(this.publicationService.newPublicationItem(null));
    }

    @Test
    @Order(3)
    void getPublicationItemById() {
        assertNotNull(this.publicationService.getPublicationItemById(availablePublicationItem.getItemId()));
        assertNull(this.publicationService.getPublicationItemById(null));
    }

    @Test
    @Order(4)
    void getPublications() {
        assertNotNull(this.publicationService.getPublications());
    }

    @Test
    @Order(5)
    void getPublicationById() {
        assertNotNull(this.publicationService.getPublicationById(publication.getPublicationId()));
        assertNull(this.publicationService.getPublicationById(null));
    }

    @Test
    @Order(6)
    void getPublicationItemsByPublicationId() {
        assertEquals(2,this.publicationService.getPublicationItemsByPublicationId(publication.getPublicationId()).size());
        assertNull(this.publicationService.getPublicationItemsByPublicationId(null));
    }

    @Test
    @Order(7)
    void getAvailablePublicationItemsByPublicationId() {
        assertEquals(1,this.publicationService.getAvailablePublicationItemsByPublicationId(publication.getPublicationId()).size());
        assertNull(this.publicationService.getAvailablePublicationItemsByPublicationId(null));
    }


    @Test
    @Order(8)
    void deletePublicationItem() {
        publicationService.deletePublicationItem(availablePublicationItem.getItemId());
        assertNull(publicationService.getPublicationItemById(availablePublicationItem.getItemId()));

        assertNull(this.publicationService.deletePublicationItem(null));
    }


    @Test
    @Order(9)
    void deletePublicationById() {
        assertThrows(DataIntegrityViolationException.class, ()-> this.publicationService.deletePublicationById(publication.getPublicationId()));
        this.publicationService.deletePublicationItem(unavailablePublicationItem.getItemId());
        this.publicationService.deletePublicationById(publication.getPublicationId());
        assertNull(this.publicationService.getPublicationById(publication.getPublicationId()));
        assertNull(this.publicationService.deletePublicationItem(null));
    }
}