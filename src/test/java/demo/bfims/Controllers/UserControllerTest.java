package demo.bfims.Controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Users.User;
import demo.bfims.Services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {
    User adminUser = new User();
    User regUser = new User();
    UUID adminUuid;
    UUID regUuid;
    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    UserService userService;

    @Autowired
    MockMvc mockMvc;

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
    void newUser() throws Exception {
        UserDto ru = new UserDto();
        ru.setEmail("reg2");
        ru.setPassword("password");

        String result = mockMvc.perform(post("/newuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ru)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertDoesNotThrow(() -> UUID.fromString(result));
    }


    @Test
    void newAdminUserAuthorized() throws Exception {
        UserDto au = new UserDto();
        au.setEmail("admin2");
        au.setPassword("password");

        mockMvc.perform(post("/newadminuser")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(au)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect((content().string("Admin User Added")));
    }

    @Test
    void newAdminUserUnauthorized() throws Exception {
        UserDto au = new UserDto();
        au.setEmail("admin2");
        au.setPassword("password");

        mockMvc.perform(post("/newadminuser")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(au)))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("Username Unavailable"));
    }

    @Test
    void getAdminUsersAuthorized() throws Exception {
        String result = mockMvc.perform(get("/adminusers")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertNotNull(objectMapper.readValue(result, new TypeReference<>() {
        }));

    }

    @Test
    void getAdminUsersUnauthorized() throws Exception {
        String result = mockMvc.perform(get("/adminusers")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.isBlank());
    }

    @Test
    void deleteUserAuthorized() throws Exception {
        String result = mockMvc.perform(delete("/deleteuser/" + regUser.getUserId())
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertTrue(result.equals("User Deleted Successfully") || result.equals("User Unavailable"));
    }

    @Test
    void deleteUserUnauthorized() throws Exception {
        String result = mockMvc.perform(delete("/deleteuser/" + regUser.getUserId())
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        assertTrue(result.isBlank());
    }

    @Test
    void authenticateUser() throws Exception {
        UserDto ru = new UserDto();
        ru.setEmail("reg3");
        ru.setPassword("password");
        userService.newUser(ru);

        String result = mockMvc.perform(post("/authenticateuser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ru)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertDoesNotThrow(() -> UUID.fromString(result));
    }

    @Test
    void reinstateUser() throws Exception {
        String result = mockMvc.perform(post("/reinstateuser")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(regUser)))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(result, regUser.getEmail());
    }

    @Test
    void getRole() throws Exception {
        String userResult = mockMvc.perform(get("/isadmin")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(userResult, regUser.getUserRole().toString());

        String adminResult = mockMvc.perform(get("/isadmin")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        assertEquals(adminResult, adminUser.getUserRole().toString());
    }

    @Test
    void logout() throws Exception {
        var resp = mockMvc.perform(post("/logout")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        assertNull(resp.getRequest().getSession(false));
    }

}