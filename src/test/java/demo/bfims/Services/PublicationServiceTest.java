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
    private ModelMapper modelMapper;

    @BeforeAll
    static void setUp() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        Publication publication = new Publication();
        publication.addAuthor(author);
        publication.setTitle("Book Title 1");
        publication.setGenre(Genre.FANTASY);

        Book book = new Book();
        book.setPublication(new Publication());
        publicationItem = book;
    }


    @Test
    @Order(1)
    void newPublicationItem() {
        ItemDto newItem = publicationService.newPublicationItem(modelMapper.map(publicationItem, PublicationItemDto.class));
        assertNotNull(publicationService.getPublicationItem(newItem.getItemId()));
    }

    @Test
    @Transactional
    @Order(2)
    void getPublicationByIdItem() {
        assertNotNull(publicationService.getPublicationItem(publicationItem.getItemId()));
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
        assertNull(publicationService.getPublicationItem(publicationItem.getItemId()));
    }
}