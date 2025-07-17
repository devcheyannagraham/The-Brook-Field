package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.DTOs.InventoryDTOs.PublicationDto;
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

//low selling/renting items
    @GetMapping("lowsales")
    public List<ItemDto> getLowSalesItems() {
        return reportService.getLowSalesItems();
    }

//Items that make the most money
    @GetMapping("/profitableitems")
    public List<ItemDto> getProfitableItems() {
        return reportService.getProfitableItems();
    }

    @GetMapping("/recentorders")
    public List<ItemDto> getRecentOrders() {
        return reportService.getRecentOrders();
    }


}
