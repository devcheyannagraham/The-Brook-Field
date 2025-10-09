package demo.bfims.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bfims.DTOs.InventoryDTOs.Publication.*;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Inventory.Publication.*;
import demo.bfims.Entities.Users.User;
import demo.bfims.Enums.ItemType;
import demo.bfims.Services.PublicationService;
import demo.bfims.Services.UserService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublicationControllerTest {
    MockMvc mockMvc;
    UserService userService;
    User adminUser = new User();
    User regUser = new User();
    UUID adminUuid;
    UUID regUuid;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    PublicationService publicationService;

    @Autowired
    public PublicationControllerTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;
    }

    @BeforeAll
    static void beforeAll() {
    }

    @BeforeEach
    void setUp() {
        UserDto au = new UserDto();
        au.setEmail("admin");
        au.setPassword("password");
        userService.saveAdminUser(au);
        adminUser = userService.authenticateUser(au);
        adminUuid = UUID.randomUUID();

        UserDto ru = new UserDto();
        ru.setEmail("reg");
        ru.setPassword("password");
        userService.newUser(ru);
        regUser = userService.authenticateUser(ru);
        regUuid = UUID.randomUUID();
    }


    @Test
    void getPublicationItemsByPublicationIdAuthorized() throws Exception {
        Mockito.when(publicationService.getPublicationItemsByPublicationId(Mockito.any())).thenReturn(List.of(new BookDto(), new JournalDto(), new LiteraryPieceDto()));
        mockMvc.perform(get("/publicationitems/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getPublicationItemsByPublicationIdUnauthorized() throws Exception {
        Mockito.when(publicationService.getPublicationItemsByPublicationId(Mockito.any())).thenReturn(null);
        mockMvc.perform(get("/publicationitems/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getAvailablePublicationItemsByPublicationId() throws Exception {
        Mockito.when(publicationService.getAvailablePublicationItemsByPublicationId(Mockito.any())).thenReturn(List.of(new BookDto(), new JournalDto(), new LiteraryPieceDto()));
        mockMvc.perform(get("/shop/publicationitems/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    void getPublications() throws Exception {
        Mockito.when(publicationService.getPublications()).thenReturn(List.of(new PublicationDto(), new PublicationDto()));
        mockMvc.perform(get("/publications")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getPublicationById() throws Exception {
        Mockito.when(publicationService.getPublicationById(Mockito.any())).thenReturn(new PublicationDto());
        mockMvc.perform(get("/publication/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getPublicationItemByIdAuthorized() throws Exception {
        Mockito.when(publicationService.getPublicationItemById(Mockito.any())).thenReturn(new PublicationItemDto());
        mockMvc.perform(get("/publication/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getPublicationItemByIdUnauthorized() throws Exception {
        Mockito.when(publicationService.getPublicationItemById(Mockito.any())).thenReturn(new PublicationItemDto());
        mockMvc.perform(get("/publication/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void newPublicationItemAuthorized() throws Exception {
        Mockito.when(publicationService.newPublicationItem(Mockito.any(PublicationItemDto.class))).thenReturn(new BookDto());
        mockMvc.perform(post("/publicationitem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookDto()))
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void newPublicationItemUnauthorized() throws Exception {
        Mockito.when(publicationService.newPublicationItem(Mockito.any(PublicationItemDto.class))).thenReturn(null);
        mockMvc.perform(post("/publicationitem")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new BookDto()))
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void newPublicationAuthorized() throws Exception {
        Mockito.when(publicationService.newPublication(Mockito.any(PublicationDto.class))).thenReturn(new PublicationDto());
        mockMvc.perform(post("/publication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PublicationDto()))
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void newPublicationUnauthorized() throws Exception {
        Mockito.when(publicationService.newPublication(Mockito.any(PublicationDto.class))).thenReturn(null);
        mockMvc.perform(post("/publication")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new PublicationDto()))
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void deletePublicationItemAuthorized() throws Exception {
        Mockito.when(publicationService.deletePublicationItem(Mockito.any())).thenReturn(true);
        mockMvc.perform(delete("/publicationitem/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deletePublicationItemUnAuthorized() throws Exception {
        Mockito.when(publicationService.deletePublicationItem(Mockito.any())).thenReturn(false);
        mockMvc.perform(delete("/publicationitem/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }

    @Test
    void deletePublicationByIdAuthenticated() throws Exception{
        Mockito.when(publicationService.deletePublicationById(Mockito.any())).thenReturn(true);
        mockMvc.perform(delete("/publication/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void deletePublicationByIdUnauthenticated() throws Exception{
        Mockito.when(publicationService.deletePublicationById(Mockito.any())).thenReturn(false);
        mockMvc.perform(delete("/publication/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}