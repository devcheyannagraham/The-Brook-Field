package demo.bfims.Controllers;

import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItemDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.DTOs.ReportDTOs.ShopPopularItemDto;
import demo.bfims.Services.ReportService;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "https://localhost:4200", allowCredentials = "true")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;

    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    //Popular items
//    item id in order items the most
    @GetMapping("/popularitems")
    public List<PopularItemDto> getPopularItems(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        if (uuid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, uuid))
            return reportService.getPopularItems();
        else return null;
    }

    @GetMapping("/shop/popularitems")
    public List<ShopPopularItemDto> getShopPopularItems() {
        return reportService.getShopPopularItems();
    }

    //low inventory items
    @GetMapping("/lowinventory")
    public List<InventoryCountDto> getLowInventoryItems(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        if (uuid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, uuid))
            return reportService.getLowInventoryItems();
        else return null;
    }

    @GetMapping("/recentorders")
    public List<RecentOrderDto> getRecentOrders(HttpServletRequest request, @RequestHeader("user-uuid") String uuid) {
        if (uuid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, uuid))
            return reportService.getRecentOrders();
        else return reportService.getRecentOrders(this.userService.getUserId(request, uuid));
    }

    @GetMapping("/search/{terms}")
    public Set<InventoryCountDto> searchItems(@PathVariable String terms) {
        if (terms.isEmpty()) return null;
        return this.reportService.searchItems(terms);
    }


}
