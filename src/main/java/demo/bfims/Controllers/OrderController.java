package demo.bfims.Controllers;

import demo.bfims.DTOs.OrderDTOs.OrderDto;
import demo.bfims.Services.OrderService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/order")
    public Boolean newOrder(@RequestBody OrderDto order) {
        return orderService.newOrder(order) != null;
    }
}
