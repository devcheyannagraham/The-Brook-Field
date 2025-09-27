package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Services.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins ="http://localhost:4200")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public void newOrder(@RequestBody OrderDto order) {
        orderService.newOrder(order);
    }
}
