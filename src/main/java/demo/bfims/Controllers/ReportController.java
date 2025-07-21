package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.DTOs.ReportDTOs.ItemGroup;
import demo.bfims.DTOs.ReportDTOs.PopularItemsDto;
import demo.bfims.Services.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    //Popular items
//    item id in order items the most
    @GetMapping("/popularitems")
    public PopularItemsDto getPopularItems() {
        return reportService.getPopularItems();
    }

    //low inventory items
    @GetMapping("/lowinventory")
    public ItemGroup getLowInventoryItems() {
        return reportService.getLowInventoryItems();
    }

    @GetMapping("/recentorders")
    public List<OrderDto> getRecentOrders() {
        return reportService.getRecentOrders();
    }
}
