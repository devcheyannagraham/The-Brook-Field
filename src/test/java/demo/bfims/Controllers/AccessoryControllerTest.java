package demo.bfims.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryDto;
import demo.bfims.DTOs.InventoryDTOs.Accessory.AccessoryItemDto;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Users.User;
import demo.bfims.Services.AccessoryService;
import demo.bfims.Services.UserService;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class AccessoryControllerTest {
    MockMvc mockMvc;
    UserService userService;
    User adminUser = new User();
    User regUser = new User();
    UUID adminUuid;
    UUID regUuid;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    AccessoryService accessoryService;


    @Autowired
    public AccessoryControllerTest(MockMvc mockMvc, UserService userService) {
        this.mockMvc = mockMvc;
        this.userService = userService;

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

    @BeforeEach
    void setUp() {

    }

    @Test
    void getAccessories() throws Exception {
        Mockito.when(accessoryService.getAccessories()).thenReturn(List.of(new AccessoryDto(), new AccessoryDto()));
        mockMvc.perform(get("/accessories")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAccessoryByIdAuthorized() throws Exception {
        Mockito.when(accessoryService.getAccessoryById(Mockito.any())).thenReturn(new AccessoryDto());
        mockMvc.perform(get("/accessory/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getAccessoryByIdUnauthorized() throws Exception {
        Mockito.when(accessoryService.getAccessoryById(Mockito.any())).thenReturn(null);
        mockMvc.perform(get("/accessory/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }


    @Test
    void getAvailableAccessoryItemsByAccessoryId() throws Exception {
        Mockito.when(accessoryService.getAvailableAccessoryItemsByAccessoryId(Mockito.any())).thenReturn(List.of(new AccessoryItemDto(), new AccessoryItemDto()));
        mockMvc.perform(get("/shop/accessory/accessoryitems/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getAccessoryItemsByAccessoryIdAuthorized() throws Exception {
        Mockito.when(accessoryService.getAccessoryItemsByAccessoryId(Mockito.any())).thenReturn(List.of(new AccessoryItemDto(), new AccessoryItemDto()));
        mockMvc.perform(get("/accessory/accessoryitems/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }


    @Test
    void getAccessoryItemsByAccessoryIdUnauthorized() throws Exception {
        Mockito.when(accessoryService.getAccessoryItemsByAccessoryId(Mockito.any())).thenReturn(null);
        mockMvc.perform(get("/accessory/accessoryitems/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void newAccessoryAuthorized() throws Exception {
        Mockito.when(accessoryService.newAccessory(Mockito.any(AccessoryDto.class))).thenReturn(new AccessoryDto());
        mockMvc.perform(post("/accessory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Accessory()))
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void newAccessoryUnauthorized() throws Exception {
        Mockito.when(accessoryService.newAccessory(Mockito.any(AccessoryDto.class))).thenReturn(null);
        mockMvc.perform(post("/accessory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Accessory()))
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void deleteAccessoryItemByIdAuthorized() throws Exception {
        Mockito.when(accessoryService.deleteAccessoryItemById(Mockito.any())).thenReturn(1);
        mockMvc.perform(delete("/accessoryitem/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    void deleteAccessoryItemByIdUnauthorized() throws Exception {
        Mockito.when(accessoryService.deleteAccessoryItemById(Mockito.any())).thenReturn(null);
        mockMvc.perform(delete("/accessoryitem/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void deleteAccessoryByIdAuthorized() throws Exception {
        Mockito.when(accessoryService.deleteAccessoryById(Mockito.any())).thenReturn(1);
        mockMvc.perform(delete("/accessory/1")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1));
    }


    @Test
    void deleteAccessoryByIdunAuthorizedById() throws Exception {
        Mockito.when(accessoryService.deleteAccessoryById(Mockito.any())).thenReturn(null);
        mockMvc.perform(delete("/accessory/1")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }
}