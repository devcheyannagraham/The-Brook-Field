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
class PublicationItemServiceTest {

    static PublicationItem publicationItem;

    @Autowired
    PublicationItemService publicationItemService;
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
        ItemDto newItem = publicationItemService.newPublicationItem(modelMapper.map(publicationItem, PublicationItemDto.class));
        assertNotNull(publicationItemService.getPublicationItem(newItem.getItemId()));
    }

    @Test
    @Transactional
    @Order(2)
    void getPublicationItem() {
        assertNotNull(publicationItemService.getPublicationItem(publicationItem.getItemId()));
    }

    @Test
    @Order(3)
    void getPublicationItems() {
        assertNotNull(publicationItemService.getPublicationItems());
    }

    @Test
    @Order(4)
    void deletePublicationItem() {
        publicationItemService.deletePublicationItem(publicationItem.getItemId());
        assertNull(publicationItemService.getPublicationItem(publicationItem.getItemId()));
    }
}