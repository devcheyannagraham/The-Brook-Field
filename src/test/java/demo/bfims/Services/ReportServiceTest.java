package demo.bfims.Services;

import demo.bfims.DTOs.OrderDTOs.CustomerDto;
import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.DTOs.OrderDTOs.RentalDto;
import demo.bfims.DTOs.ReportDTOs.InventoryCountDto;
import demo.bfims.DTOs.ReportDTOs.RecentOrderDto;
import demo.bfims.Entities.Inventory.Publication.Author;
import demo.bfims.Entities.Inventory.Publication.Book;
import demo.bfims.Entities.Inventory.Publication.Publication;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Entities.Order.Rental;
import demo.bfims.Enums.Genre;
import demo.bfims.Enums.PublicationItemFormat;
import demo.bfims.Enums.PublicationItemStatus;
import demo.bfims.Repo.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ReportServiceTest {
    // Use bootstrap data to test

    ReportService reportService;

    @Autowired
    public ReportServiceTest(ReportService reportService) {
        this.reportService = reportService;
    }

    @Test
    void getShopPopularItems() {
        assertNotNull(this.reportService.getPopularItems());
    }


    @Test
    void getLowInventoryItems() {
        assertTrue(this.reportService.getLowInventoryItems().stream().filter(item -> item.getCount() > 5).toList().isEmpty());
    }

    @Test
    void getRecentOrders() {
        assertTrue(this.reportService.getRecentOrders().stream().filter(order -> order.getOrderDate().isBefore(LocalDateTime.now().minusMonths(3))).toList().isEmpty());
    }

    @Test
    void searchItems() {
        assertNotNull(this.reportService.searchItems("a"));
        assertNull(this.reportService.searchItems(""));
        assertNull(this.reportService.searchItems(" "));
    }

}