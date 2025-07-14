package demo.bfims.Controllers;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Services.ReportSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReportController {

    private ReportSerivce reportSerivce;

    public ReportController(ReportSerivce reportSerivce) {
        this.reportSerivce = reportSerivce;
    }

    //Popular items
//    item id in order items the most
    @GetMapping("/popularitems")
    public List<ItemDto> getPopularItems() {
        return reportSerivce.getPopularItems();
    }

    //low inventory items
    @GetMapping("/lowinventory")
    public List<ItemDto> getLowInventoryItems() {
        return reportSerivce.getLowInventoryItems();
    }

//low selling/renting items
    @GetMapping("lowsales")
    public List<ItemDto> getLowSalesItems() {
        return reportSerivce.getLowSalesItems();
    }

//Items that make the most money
    @GetMapping("/profitableitems")
    public List<ItemDto> getProfitableItems() {
        return reportSerivce.getProfitableItems();
    }

    @GetMapping("/recentorders")
    public List<ItemDto> getRecentOrders() {
        return reportSerivce.getRecentOrders();
    }


}
