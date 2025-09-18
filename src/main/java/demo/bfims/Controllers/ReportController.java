package demo.bfims.Controllers;

import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.PopularItemDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.Services.ReportService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    //Popular items
//    item id in order items the most
    @GetMapping("/popularitems")
    public List<PopularItemDto> getPopularItems() {
        return reportService.getPopularItems();
    }

    //low inventory items
    @GetMapping("/lowinventory")
    public List<InventoryCountDto> getLowInventoryItems() {
        return reportService.getLowInventoryItems();
    }

    @GetMapping("/recentorders")
    public List<RecentOrderDto> getRecentOrders() {
        return reportService.getRecentOrders();
    }
}
