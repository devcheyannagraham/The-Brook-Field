package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Entities.Inventory.Book;
import demo.bfims.Entities.Inventory.Item;
import demo.bfims.Entities.Inventory.PublicationItem;
import demo.bfims.Entities.Order.Customer;
import demo.bfims.Entities.Order.Order;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ItemRepo itemRepo;

    @GetMapping("/orders")
    public List<OrderDto> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/order/{id}")
    public OrderDto getOrder(@PathVariable Long id) {
        return orderService.getOrder(id);
    }

    @GetMapping("/orders/{customerid}")
    public List<OrderDto> getCustomerOrders(Long id) {
        return orderService.getCustomerOrders(id);
    }

    @PostMapping("/order")
    public OrderDto newOrder(@RequestBody OrderDto order) {
        return orderService.newOrder(order);
    }


}
