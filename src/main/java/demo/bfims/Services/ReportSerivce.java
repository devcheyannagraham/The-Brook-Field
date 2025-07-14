package demo.bfims.Services;

import demo.bfims.DTOs.InventoryDTOs.ItemDto;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Repo.OrderRepo;
import demo.bfims.Repo.PublicationRepo;
import demo.bfims.Repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReportSerivce {
    @Autowired
    ItemRepo itemRepo;
    @Autowired
    OrderRepo orderRepo;
    @Autowired
    PublicationRepo publicationRepo;
    @Autowired
    TransactionRepo transactionRepo;

    public List<ItemDto> getPopularItems() {
        System.out.println("Popular Items");
        return null;
    }

    public List<ItemDto> getLowInventoryItems() {
        List<ItemDto> items = new ArrayList<>();
        System.out.println("Low Inventory Items");
        return null;
    }

    //low selling/renting items
    public List<ItemDto> getLowSalesItems() {
        System.out.println("Low Sales Items");
        return null;
    }

    //Items that make the most money
    public List<ItemDto> getProfitableItems() {
        System.out.println("Profitable Items");
        return null;
    }

    public List<ItemDto> getRecentOrders() {
        System.out.println("Recent Orders");
        return null;
    }






}
