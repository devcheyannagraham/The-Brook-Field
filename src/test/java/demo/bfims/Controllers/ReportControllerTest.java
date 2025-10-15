package demo.bfims.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItemDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.DTOs.ReportDTOs.ShopPopularItemDto;
import demo.bfims.DTOs.User.UserDto;
import demo.bfims.Entities.Inventory.Accessory.Accessory;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Users.User;
import demo.bfims.Services.ReportService;
import demo.bfims.Services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReportControllerTest {

    MockMvc mockMvc;
    UserService userService;
    User adminUser = new User();
    User regUser = new User();
    UUID adminUuid;
    UUID regUuid;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockitoBean
    ReportService reportService;

    @Autowired
    public ReportControllerTest(MockMvc mockMvc, UserService userService) {
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

    @Test
    void getPopularItemsAuthorized() throws Exception {
        Mockito.when(reportService.getPopularItems()).thenReturn(List.of(new PopularItemDto(), new PopularItemDto()));
        mockMvc.perform(get("/api/popularitems")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getPopularItemsUnauthorized() throws Exception {
        Mockito.when(reportService.getPopularItems()).thenReturn(null);
        mockMvc.perform(get("/api/popularitems")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getShopPopularItems() throws Exception {
        Mockito.when(reportService.getShopPopularItems()).thenReturn(List.of(new ShopPopularItemDto(new PopularItemDto()), new ShopPopularItemDto(new PopularItemDto())));
        mockMvc.perform(get("/api/shop/popularitems")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getLowInventoryItemsAuthorized() throws Exception {
        Mockito.when(reportService.getLowInventoryItems()).thenReturn(List.of(new InventoryCountDto(new Publication(), 2), new InventoryCountDto(new Accessory(), 3)));
        mockMvc.perform(get("/api/lowinventory")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void getLowInventoryItemsUnauthorized() throws Exception {
        Mockito.when(reportService.getLowInventoryItems()).thenReturn(null);
        mockMvc.perform(get("/api/lowinventory")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    void getUserRecentOrders() throws Exception {
        RecentOrderDto rco1 = new RecentOrderDto();
        RecentOrderDto rco2 = new RecentOrderDto();
        rco1.setCustomerEmail(adminUser.getEmail());
        rco2.setCustomerEmail(regUser.getEmail());

        Mockito.when(reportService.getRecentOrders()).thenReturn(List.of(rco1, rco2));

        //Admin gets all users recent orders
        mockMvc.perform(get("/api/userrecentorders")
                        .header("user-uuid", adminUuid.toString())
                        .sessionAttr(adminUuid.toString(), adminUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));

        //User has no orders in mocked service so expect 0;
        mockMvc.perform(get("/api/userrecentorders")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }


    @Test
    void searchItems() throws Exception {
        Mockito.when(reportService.searchItems(Mockito.any())).thenReturn(Set.of(new InventoryCountDto(new Accessory(), 2), new InventoryCountDto(new Accessory(), 3)));
        mockMvc.perform(get("/api/search/a")
                        .header("user-uuid", regUuid.toString())
                        .sessionAttr(regUuid.toString(), regUser.getUserId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}