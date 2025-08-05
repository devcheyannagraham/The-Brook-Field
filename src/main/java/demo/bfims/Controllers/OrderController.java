package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Repo.ItemRepo;
import demo.bfims.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
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
    public List<OrderDto> getCustomerOrders(@PathVariable Long customerid) {
        return orderService.getCustomerOrders(customerid);
    }

    @PostMapping("/order")
    public OrderDto newOrder(@RequestBody OrderDto order) {
        return orderService.newOrder(order);
    }


}
