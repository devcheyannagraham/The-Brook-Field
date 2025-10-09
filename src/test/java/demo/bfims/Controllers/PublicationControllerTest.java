package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.Publication.PublicationItemDto;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import demo.bfims.Repo.UserRepo;
import demo.bfims.Services.PublicationService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest
class PublicationControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    PublicationRepo publicationRepo;

    @Autowired
    ItemRepo itemRepo;

    @Autowired
    PublicationService publicationService;

    @Autowired
    UserRepo userRepo;

    static Publication publication;
    static PublicationItem availablePublicationItem;
    static PublicationItem unavailablePublicationItem;

    static User adminUser;
    static User user;

    @BeforeAll
    static void beforeAll() {
//        Publication pub = new Publication();
//        pub.setAuthor(new Author("John", "Doe"));
//        pub.setTitle("Book Title 1");
//        pub.setGenre(Genre.FANTASY);
//
//        Book availableBook = new Book();
//        availableBook.setPublication(publication);
//        availableBook.setPublicationItemStatus(PublicationItemStatus.AVAILABLE);
//        availablePublicationItem = new Book(publicationService.newPublicationItem(new PublicationItemDto(availableBook)));
//        assertNotNull(availablePublicationItem);
//
//        Journal unavailableJournal = new Journal();
//        unavailableJournal.setPublication(publication);
//        unavailableJournal.setPublicationItemStatus(PublicationItemStatus.RENTED);
//        unavailablePublicationItem = new Journal(publicationService.newPublicationItem(new PublicationItemDto(unavailableJournal)));
//        assertNotNull(unavailablePublicationItem);


    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void getPublicationItemsByPublicationId() {
    }

    @Test
    void getAvailablePublicationItemsByPublicationId() {
    }

    @Test
    void getPublications() {
    }

    @Test
    void getPublicationById() {
    }

    @Test
    void getPublicationItemById() {
    }

    @Test
    void newPublicationItem() {
    }

    @Test
    void newPublication() {
    }

    @Test
    void deletePublicationItem() {
    }

    @Test
    void deletePublicationById() {
    }
}