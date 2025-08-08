package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.Publication.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Entities.Inventory.Publication.Author;
import demo.bfims.Entities.Inventory.Publication.Book;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Inventory.Publication.PublicationItem;
import demo.bfims.Enums.Genre;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PublicationServiceTest {

    static PublicationItem publicationItem;

    @Autowired
    PublicationService publicationService;

    @BeforeAll
    static void setUp() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        Publication publication = new Publication();
        publication.setAuthor(author);
        publication.setTitle("Book Title 1");
        publication.setGenre(Genre.FANTASY);

        Book book = new Book();
        book.setPublication(publication);
        publicationItem = book;
    }


    @Test
    @Order(1)
    void newPublicationItem() {
        ItemDto newItem = publicationService.newPublicationItem(new PublicationItemDto(publicationItem));
        assertNotNull(publicationService.getPublicationItemById(newItem.getItemId()));
    }

    @Test
    @Transactional
    @Order(2)
    void getPublicationByIdItem() {
        assertNotNull(publicationService.getPublicationItemById(publicationItem.getItemId()));
    }

    @Test
    @Order(3)
    void getPublicationByIdItems() {
        assertNotNull(publicationService.getPublicationItems());
    }

    @Test
    @Order(4)
    void deletePublicationItem() {
        publicationService.deletePublicationItem(publicationItem.getItemId());
        assertNull(publicationService.getPublicationItemById(publicationItem.getItemId()));
    }
}