package demo.bfims.Controllers;

import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Users.User;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.PublicationRepo;
import demo.bfims.Repo.UserRepo;
import demo.bfims.Services.PublicationService;
import demo.bfims.Services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.setup.SharedHttpSessionConfigurer.sharedHttpSession;

@WebMvcTest(PublicationController.class)
class PublicationControllerTest {
    MockMvc mockMvc;
    PublicationRepo publicationRepo;
    ItemRepo itemRepo;
    PublicationService publicationService;

    @MockitoBean
    UserRepo userRepo;
    @MockitoBean
    UserService userService;

    @Autowired
    public PublicationControllerTest(MockMvc mockMvc, PublicationRepo publicationRepo, ItemRepo itemRepo, PublicationService publicationService, UserRepo userRepo) {
        this.mockMvc = mockMvc;
        this.publicationRepo = publicationRepo;
        this.itemRepo = itemRepo;
        this.publicationService = publicationService;
        this.userRepo = userRepo;
    }


    @BeforeAll
    static void beforeAll() {

    }

//    @BeforeEach
//    void setup(WebApplicationContext webApplicationContext) {
////        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
////                .apply(sharedHttpSession())
////                .build();
//
//    }

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