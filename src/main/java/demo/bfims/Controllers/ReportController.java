package demo.bfims.Controllers;

import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItemDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.DTOs.ReportDTOs.ShopPopularItemDto;
import demo.bfims.Services.ReportService;
import demo.bfims.Services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    private final ReportService reportService;
    private final UserService userService;

    public ReportController(ReportService reportService, UserService userService) {
        this.reportService = reportService;
        this.userService = userService;
    }

    //Popular items
//    item id in order items the most
    @GetMapping("/popularitems/{userUid}")
    public List<PopularItemDto> getPopularItems(HttpServletRequest request, @PathVariable String userUid) {
        if (userUid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, userUid))
            return reportService.getPopularItems();
        else return null;
    }

    @GetMapping("/shop/popularitems")
    public List<ShopPopularItemDto> getShopPopularItems() {
        return reportService.getShopPopularItems();
    }

    //low inventory items
    @GetMapping("/lowinventory/{userUid}")
    public List<InventoryCountDto> getLowInventoryItems(HttpServletRequest request, @PathVariable String userUid) {
        if (userUid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, userUid))
            return reportService.getLowInventoryItems();
        else return null;
    }

    @GetMapping("/recentorders/{userUid}")
    public List<RecentOrderDto> getRecentOrders(HttpServletRequest request, @PathVariable String userUid) {
        if (userUid == null || request == null) return null;
        if (this.userService.isSessionUserAdmin(request, userUid))
            return reportService.getRecentOrders();
        else return reportService.getRecentOrders(this.userService.getUserId(request, userUid));
    }

}
